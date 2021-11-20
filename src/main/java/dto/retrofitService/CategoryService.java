package dto.retrofitService;

import dto.retrofitDto.Category;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryService {

    @GET("categories/{id}")
    Call<Category> readCategory(@Path("id") Integer id);
}