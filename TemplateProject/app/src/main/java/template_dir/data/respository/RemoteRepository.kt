package template_package.data.respository

import template_package.data.db.entities.Categories
import template_package.data.db.entities.CategoryImages
import template_package.data.models.CustomResult
import template_package.data.network.Api
import template_package.data.network.SafeApiRequest
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val client: Api
) : SafeApiRequest() {
    suspend fun getCategories(): CustomResult<Pair<List<Categories>, List<CategoryImages>>> {
        return when (val result = apiRequest { client.getCategories() }) {
            is CustomResult.Success -> {
                when (val imageResult = apiRequest { client.getCategoryImages() }) {
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

}