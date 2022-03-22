package template_package.data.network

import template_package.data.models.CommonResponse
import retrofit2.Response
import retrofit2.http.GET
import template_package.data.localdb.entities.Categories
import template_package.data.localdb.entities.CategoryImages
import template_package.data.models.CategoriesRemote
import template_package.data.models.CategoryImagesRemote

interface Api {

    //TODO: remove this class if you are NOT USING ~~~~~~~ LOCAL DATABASE ~~~~~~~
    @GET("categories")
    suspend fun getCategoriesRemoteLocal(): Response<CommonResponse<List<Categories>>>

    //TODO: remove this class if you are NOT USING ~~~~~~~ LOCAL DATABASE ~~~~~~~
    @GET("category_images")
    suspend fun getCategoryImagesRemoteLocal(): Response<CommonResponse<List<CategoryImages>>>

    //TODO: remove this class if you are USING ~~~~~~~ LOCAL DATABASE ~~~~~~~
    @GET("categories")
    suspend fun getCategoriesRemote(): Response<CommonResponse<List<CategoriesRemote>>>

    //TODO: remove this class if you are USING ~~~~~~~ LOCAL DATABASE ~~~~~~~
    @GET("category_images")
    suspend fun getCategoryImagesRemote(): Response<CommonResponse<List<CategoryImagesRemote>>>

}