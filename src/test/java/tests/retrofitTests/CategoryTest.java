package tests.retrofitTests;

import com.github.javafaker.Faker;
import db.dao.CategoriesMapper;
import db.model.Categories;
import dto.retrofitDto.Category;
import dto.retrofitDto.Product;
import dto.retrofitEnums.CategoryType;
import dto.retrofitService.CategoryService;
import dto.retrofitUtils.DbUtils;
import dto.retrofitUtils.PrettyLogger;
import dto.retrofitUtils.RetrofitUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CategoryTest {

    static Retrofit client;
    static CategoryService categoryService;
    static CategoriesMapper categoriesMapper;
    Faker faker = new Faker();
    Product product;

    @BeforeAll
    static void beforeAll() {
        client = RetrofitUtils.getRetrofit();
        categoryService = client.create(CategoryService.class);
        categoriesMapper = DbUtils.getCategoriesMapper();
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().fruit())
                .withPrice((int) ((Math.random() + 1) * 100))
                .withCategoryTitle(CategoryType.FOOD.getTitle());
    }

    @Test
    void readCategoryTest() throws IOException {
        Integer id = CategoryType.FOOD.getId();
        Response<Category> response = categoryService
                .readCategory(id)
                .execute();
        PrettyLogger.DEFAULT.log(response.toString());
        assertThat(response.body().getTitle(), equalTo(CategoryType.FOOD.getTitle()));
        assertThat(response.code(), equalTo(200));
        for (Product p : response.body().getProducts()
        ) {
            assertThat(p.getCategoryTitle(), equalTo(CategoryType.FOOD.getTitle()));
        }
    }

    @Test
    void readCategoryDBTest() {
        Integer id = CategoryType.FOOD.getId();
        Categories categories = DbUtils.selectCategoryId(categoriesMapper, id);
        assertThat(categories.getTitle(), equalTo(CategoryType.FOOD.getTitle()));
    }
}