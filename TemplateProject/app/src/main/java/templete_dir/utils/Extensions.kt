package templete_dir.utils

import android.app.Activity
import android.os.Handler
import android.os.Looper
import java.io.IOException

//TODO: remove the pre-defined extensions if not needed

fun Long.postDelayed(handlerData: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        handlerData.invoke()
    }, this)
}

fun Activity.getActivity() = this

val Exception?.localizedException: LocalisedException
    get() {
        return when (this) {
            is LocalisedException -> this
            is IOException -> NoInternetException(message)
            else -> SomethingWentWrongException(this?.localizedMessage)
        }
    }