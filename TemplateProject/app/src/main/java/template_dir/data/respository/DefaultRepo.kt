package template_package.data.respository

import template_package.data.localdb.GenieDatabase
import template_package.data.models.CustomResult
import template_package.utils.Constants.SUCCESS
import javax.inject.Inject

//TODO: remove this class if you are NOT USING ~~~~~~~ LOCAL DATABASE ~~~~~~~
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
                CustomResult.Success(SUCCESS)
            }
            is CustomResult.Error -> {
                CustomResult.Error(result.exception)
            }
        }
    }

}