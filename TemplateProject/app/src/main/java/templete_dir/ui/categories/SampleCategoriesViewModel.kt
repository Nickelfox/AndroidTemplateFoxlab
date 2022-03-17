package templete_dir.ui.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import templete_dir.data.respository.DefaultRepo
import templete_dir.data.models.CustomResult
import templete_dir.utils.LocalisedException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SampleCategoriesViewModel @Inject constructor(private val repo: DefaultRepo) : ViewModel() {

    private val error = MutableLiveData<LocalisedException>()

    init {
        updateCategories()
    }

    fun observeCategories() = repo.observeCategories()

    private fun updateCategories() {
        viewModelScope.launch {
            when (val result = repo.getCategories()) {
                is CustomResult.Success -> {

                }
                is CustomResult.Error -> {
                    error.postValue(result.exception)
                }
            }
        }
    }


}