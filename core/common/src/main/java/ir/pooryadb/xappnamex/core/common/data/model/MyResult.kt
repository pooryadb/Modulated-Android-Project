package ir.pooryadb.xappnamex.core.common.data.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class MyResult<out R> {

    data class Success<out T>(val data: T) : MyResult<T>()
    data class Error(val throwable: Throwable) : MyResult<Nothing>()
    object Loading : MyResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$throwable]"
            is Loading -> "Loading"
        }
    }

    fun <T> Flow<T>.asResult(): Flow<MyResult<T>> {
        return this
            .map<T, MyResult<T>> {
                Success(it)
            }
            .onStart { emit(Loading) }
            .catch { emit(Error(it)) }
    }

}

/**
 * `true` if [MyResult] is of type [Success] & holds non-null [Success.data].
 */
val MyResult<*>.succeeded
    get() = this is MyResult.Success && data != null
