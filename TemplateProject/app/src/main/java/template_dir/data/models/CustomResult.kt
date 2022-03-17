package template_package.data.models

import template_package.utils.LocalisedException


sealed class CustomResult<out R> {
    data class Success<out T>(val data: T) : CustomResult<T>()
    data class Error(
        val exception: LocalisedException,
    ) : CustomResult<Nothing>()
}
