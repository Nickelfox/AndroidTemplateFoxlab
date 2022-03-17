package templete_dir.data.network

import templete_dir.data.db.entities.Categories
import templete_dir.data.db.entities.CategoryImages
import templete_dir.data.models.CommonResponse
import retrofit2.Response
import retrofit2.http.GET


//TODO: these are just sample calls for demonstration, replace them with your own
interface Api {

    @GET("categories")
    suspend fun getCategories(): Response<CommonResponse<List<Categories>>>

    @GET("category_images")
    suspend fun getCategoryImages(): Response<CommonResponse<List<CategoryImages>>>

}