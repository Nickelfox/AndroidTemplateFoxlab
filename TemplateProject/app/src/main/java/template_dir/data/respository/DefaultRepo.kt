package template_package.data.respository

import template_package.data.db.GenieDatabase
import template_package.data.models.CustomResult
import javax.inject.Inject

class DefaultRepo @Inject constructor(
    private val remoteRepo: RemoteRepository,
    private val database: GenieDatabase
) {

    fun observeCategories() = database.categoryDao().getCategoriesWithImages()

    suspend fun getCategories(): CustomResult<String> {
        return when (val result = remoteRepo.getCategories()) {
            is CustomResult.Success -> {
                database.categoryDao().insertUpdateCategories(result.data.first)
                database.categoryDao().insertUpdateCategoryImages(result.data.second)
                CustomResult.Success("Success")
            }
            is CustomResult.Error -> {
                CustomResult.Error(result.exception)
            }
        }
    }

}