package dto.retrofitUtils;

import com.github.javafaker.Faker;
import db.dao.CategoriesMapper;
import db.dao.ProductsMapper;
import db.model.Categories;
import db.model.Products;
import db.model.ProductsExample;
import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.List;

public class DbUtils {

    private static final String resource = "mybatisConfig.xml";
    static Faker faker = new Faker();

    private static SqlSession getSqlSession() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource));
        return sqlSessionFactory.openSession(true);
    }

    @SneakyThrows
    public static CategoriesMapper getCategoriesMapper() {return getSqlSession().getMapper(CategoriesMapper.class);}

    @SneakyThrows
    public static ProductsMapper getProductsMapper() {return getSqlSession().getMapper(ProductsMapper.class);}

    private static void createNewCategory(CategoriesMapper categoriesMapper) {
        Categories newCategory = new Categories();
        newCategory.setTitle(faker.animal().name());
        categoriesMapper.insert(newCategory);
    }

    public static Categories selectCategoryId(CategoriesMapper categoriesMapper, int id) {
        Categories category = categoriesMapper.selectByPrimaryKey(id);
        return category;
    }

    public static Integer countProducts(ProductsMapper productsMapper) {
        long products = productsMapper.countByExample(new ProductsExample());
        return Math.toIntExact(products);
    }

    public static Products selectProductId(ProductsMapper productsMapper,long id) {
        Products product = productsMapper.selectByPrimaryKey(id);
        return product;
    }

    public static List<Products> selectProducts(ProductsMapper productsMapper) {
        List<Products> products = productsMapper.selectByExample(new ProductsExample());
        return products;
    }

    public static int updateProductId(ProductsMapper productsMapper, Products record) {
        int product = productsMapper.updateByPrimaryKey(record);
        return product;
    }

    public static int deleteProductId(ProductsMapper productsMapper,long id) {
        int product = productsMapper.deleteByPrimaryKey(id);
        return product;
    }





}
