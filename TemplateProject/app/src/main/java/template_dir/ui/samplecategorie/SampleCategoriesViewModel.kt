package template_package.ui.samplecategorie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import template_package.data.respository.DefaultRepo
import template_package.data.models.CustomResult
import template_package.utils.LocalisedException
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