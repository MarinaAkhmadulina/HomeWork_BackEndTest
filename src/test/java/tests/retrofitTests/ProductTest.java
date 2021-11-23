package tests.retrofitTests;

import com.github.javafaker.Faker;
import db.dao.ProductsMapper;
import db.model.Products;
import dto.retrofitDto.Product;
import dto.retrofitEnums.CategoryType;
import dto.retrofitService.ProductService;
import dto.retrofitUtils.DbUtils;
import dto.retrofitUtils.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProductTest {

    static Retrofit client;
    static ProductService productService;
    static ProductsMapper productsMapper;
    Faker faker = new Faker();
    Product product;
    int id;
    Response<Product> response;
    Response<Product> responseCreate;

    @SneakyThrows
    public Response<Product> createProduct() {
        responseCreate = productService.createProduct(product).execute();
        id = responseCreate.body().getId();
        product.setId(id);
        return responseCreate;
    }

    @BeforeAll
    static void beforeAll() {
        client = RetrofitUtils.getRetrofit();
        productService = client.create(ProductService.class);
        productsMapper = DbUtils.getProductsMapper();
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().fruit())
                .withPrice((int) ((Math.random() + 1) * 10))
                .withCategoryTitle(CategoryType.FOOD.getTitle());
    }

    @SneakyThrows
    @AfterEach
    void comeBack() {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @Test
    void createProductTest() {
        Integer countProductsBefore = DbUtils.countProducts(productsMapper);
        responseCreate = createProduct();
        Integer countProductsAfter = DbUtils.countProducts(productsMapper);
        Assertions.assertAll(
                () -> assertThat(countProductsAfter, equalTo(countProductsBefore + 1)),
                () -> assertThat(responseCreate.body().getTitle(), equalTo(product.getTitle())),
                () -> assertThat(responseCreate.body().getPrice(), equalTo(product.getPrice())),
                () -> assertThat(responseCreate.body().getPrice(), equalTo(product.getPrice())),
                () -> assertThat(responseCreate.body().getCategoryTitle(), equalTo(product.getCategoryTitle())),
                () -> assertThat(responseCreate.isSuccessful(), CoreMatchers.is(true)),
                () -> assertThat(responseCreate.code(), equalTo(201)));
    }

    @Test
    void readProductsTest() throws IOException {
        responseCreate = createProduct();
        Response<ArrayList<Product>> response = productService.readProducts().execute();
        List<Products> productsDB = DbUtils.selectProducts(productsMapper);
        Product productRes = null;
        for (Product p : response.body()
        ) {
            if (p.getId().equals(product.getId())) {
                productRes = p;
                break;
            }
        }
        assertThat(response.body().size(), equalTo(productsDB.size()));
        assertThat(productRes, equalTo(product));
        assertThat(response.code(), equalTo(200));
    }

    @Test
    void readProductIdTest() throws IOException {
        responseCreate = createProduct();
        response = productService.readProduct(id).execute();
        Products productIdDB = DbUtils.selectProductId(productsMapper, id);
        Assertions.assertAll(
                () -> assertThat(response.body().getId(), equalTo(productIdDB.getId().intValue())),
                () -> assertThat(response.body().getTitle(), equalTo(productIdDB.getTitle())),
                () -> assertThat(response.body().getPrice(), equalTo(productIdDB.getPrice())));
        assertThat(response.body().getId(), equalTo(product.getId()));
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), equalTo(200));
    }

    @Test
    void updateProductTest() throws IOException {
        responseCreate = createProduct();
        setUp();
        product.setId(id);
        response = productService.updateProduct(product).execute();
        assertThat(response.body(), equalTo(product));
        assertThat(response.code(), equalTo(200));
    }

    @Test
    void updateProductDBTest() {
        responseCreate = createProduct();
        setUp();
        product.setId(id);
        Products productIdDB = DbUtils.selectProductId(productsMapper, id);
        productIdDB.setTitle(product.getTitle());
        productIdDB.setPrice(product.getPrice());
        DbUtils.updateProductId(productsMapper, productIdDB);
        Products productIdDBUpdated = DbUtils.selectProductId(productsMapper, id);
        Assertions.assertAll(
                () -> assertThat(productIdDBUpdated.getId(), equalTo(productIdDB.getId())),
                () -> assertThat(productIdDBUpdated.getTitle(), equalTo(productIdDB.getTitle())),
                () -> assertThat(productIdDBUpdated.getPrice(), equalTo(productIdDB.getPrice())),
                () -> assertThat(productIdDBUpdated.getCategory_id(), equalTo(productIdDB.getCategory_id())));
    }
}