package tests.retrofitTests;

import com.github.javafaker.Faker;
import db.dao.ProductsMapper;
import dto.retrofitDto.Product;
import dto.retrofitEnums.CategoryType;
import dto.retrofitService.ProductService;
import dto.retrofitUtils.DbUtils;
import dto.retrofitUtils.PrettyLogger;
import dto.retrofitUtils.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteProductTest {

    static Retrofit client;
    static ProductService productService;
    static ProductsMapper productsMapper;
    Faker faker = new Faker();
    Product product;
    int id;
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

    @Test
    void deleteProductTest() throws IOException {
        responseCreate = createProduct();
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        PrettyLogger.DEFAULT.log(response.toString());
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), equalTo(200));
    }

    @Test
    void deleteProductDBTest() {
        responseCreate = createProduct();
        Integer countProductsBefore = DbUtils.countProducts(productsMapper);
        DbUtils.deleteProductId(productsMapper, id);
        Integer countProductsAfter = DbUtils.countProducts(productsMapper);
        assertThat(countProductsAfter, equalTo(countProductsBefore - 1));
    }
}