package template_package.data.respository

import template_package.data.localdb.entities.Categories
import template_package.data.localdb.entities.CategoryImages
import template_package.data.models.CategoriesModel
import template_package.data.models.CustomResult
import template_package.data.network.Api
import template_package.data.network.SafeApiRequest
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val client: Api
) : SafeApiRequest() {

    //TODO: remove this method if you are NOT USING ~~~~~~~ LOCAL DATABASE ~~~~~~~
    suspend fun getCategories(): CustomResult<Pair<List<Categories>, List<CategoryImages>>> {
        return when (val result = apiRequest { client.getCategoriesRemoteLocal() }) {
            is CustomResult.Success -> {
                when (val imageResult = apiRequest { client.getCategoryImagesRemoteLocal() }) {
                    is CustomResult.Success -> {
                        CustomResult.Success(Pair(result.data.data, imageResult.data.data))
                    }
                    is CustomResult.Error -> {
                        CustomResult.Error(imageResult.exception)
                    }
                }
            }
            is CustomResult.Error -> {
                CustomResult.Error(result.exception)
            }
        }
    }

    //TODO: remove this method if you are USING ~~~~~~~ LOCAL DATABASE ~~~~~~~
    suspend fun getCategoriesRemote(): CustomResult<List<CategoriesModel>> {
        return when (val result = apiRequest { client.getCategoriesRemote() }) {
            is CustomResult.Success -> {
                when (val imageResult = apiRequest { client.getCategoryImagesRemote() }) {
                    is CustomResult.Success -> {
                        val map = result.data.data.map { category ->
                            CategoriesModel(imageResult.data.data.find {
                                it.imageId == category.categoryId
                            }?.imageUrl ?: "", category.categoryDisplayName, category.isActive)
                        }
                        CustomResult.Success(map)
                    }
                    is CustomResult.Error -> {
                        CustomResult.Error(imageResult.exception)
                    }
                }
            }
            is CustomResult.Error -> {
                CustomResult.Error(result.exception)
            }
        }
    }

}