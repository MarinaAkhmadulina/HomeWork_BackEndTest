package tests.retrofitTests;

import com.github.javafaker.Faker;
import dto.retrofitDto.Product;
import dto.retrofitEnums.CategoryType;
import dto.retrofitService.ProductService;
import dto.retrofitUtils.PrettyLogger;
import dto.retrofitUtils.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProductTest {

    static Retrofit client;
    static ProductService productService;
    Faker faker = new Faker();
    Product product;

    int id;
    Response<Product> response;
    Response<Product> responseCreate;

    @SneakyThrows
    public  Response <Product> createProduct(){
        responseCreate = productService.createProduct(product).execute();
        id = responseCreate.body().getId();
        product.setId(id);
        return responseCreate;
    }

    @BeforeAll
    static void beforeAll() {
        client = RetrofitUtils.getRetrofit();
        productService = client.create(ProductService.class);
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
        responseCreate = createProduct();
        PrettyLogger.DEFAULT.log(responseCreate.toString());
        assertThat(responseCreate.body().getTitle(),equalTo(product.getTitle()));
        assertThat(responseCreate.body().getPrice(),equalTo(product.getPrice()));
        assertThat(responseCreate.body().getCategoryTitle(),equalTo(product.getCategoryTitle()));
        assertThat(responseCreate.isSuccessful(), CoreMatchers.is(true));
        assertThat(responseCreate.code(), equalTo(201));
    }

    @Test
    void readProductsTest() throws IOException {
        responseCreate = createProduct();
        Response<ArrayList<Product>> response = productService.readProducts().execute();
        PrettyLogger.DEFAULT.log(response.toString());
        Product productRes = null;
        for (Product p: response.body()
             ) {
            if (p.getId().equals(product.getId())){
                productRes = p;
            }
        }
        assertThat(productRes, equalTo(product));
        assertThat(response.code(), equalTo(200));
    }

    @Test
    void readProductIdTest() throws IOException {
        responseCreate = createProduct();
        response = productService.readProduct(id).execute();
        PrettyLogger.DEFAULT.log(response.toString());
        assertThat(response.body().getId(),equalTo(product.getId()));
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), equalTo(200));
    }

    @Test
    void updateProductTest() throws IOException {
        responseCreate = createProduct();
        setUp();
        product.setId(id);
        response = productService.updateProduct(product).execute();
        PrettyLogger.DEFAULT.log(response.toString());
        assertThat(response.body(),equalTo(product));
        assertThat(response.code(), equalTo(200));
    }

    @Test
    void deleteProductTest() throws IOException {
        responseCreate = createProduct();
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        PrettyLogger.DEFAULT.log(response.toString());
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), equalTo(200));
    }
}