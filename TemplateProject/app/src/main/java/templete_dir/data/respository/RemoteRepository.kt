package templete_dir.data.respository

import templete_dir.data.db.entities.Categories
import templete_dir.data.db.entities.CategoryImages
import templete_dir.data.models.CustomResult
import templete_dir.data.network.Api
import templete_dir.data.network.SafeApiRequest
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