package dto.retrofitService;

import dto.retrofitDto.Product;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import java.util.ArrayList;

public interface ProductService {

    @POST("products")
    Call<Product>createProduct(@Body Product product);

    @GET("products")
    Call<ArrayList<Product>>readProducts();

    @GET("products/{id}")
    Call<Product>readProduct(@Path("id") Integer id);

    @PUT ("products")
    Call<Product>updateProduct(@Body Product product);

    @DELETE ("products/{id}")
    Call<ResponseBody>deleteProduct(@Path("id") Integer id);
}