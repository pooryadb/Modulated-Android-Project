package ir.pooryadb.xappnamex.core.baseUi

import android.content.Context
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.pooryadb.xappnamex.core.baseUi.utils.liveData.SingleLiveData
import ir.pooryadb.xappnamex.core.common.data.model.MyResult
import ir.pooryadb.xappnamex.core.common.data.model.MyResult.Loading.asResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    protected open val TAG = this::class.java.simpleName

    protected val _liveMessage = SingleLiveData<MessageResult>()
    val liveMessage: LiveData<MessageResult>
        get() = _liveMessage

    fun <T> observeFlow(
        flow: Flow<T>,
        observeFunction: (T) -> Unit,
    ) = viewModelScope.launch {
        flow.collect {
            observeFunction(it)
        }
    }

    fun <T> observeResult(
        flow: Flow<T>,
        observeFunction: (T) -> Unit,
    ) = viewModelScope.launch {
        flow.asResult().collect {
            when (it) {
                is MyResult.Error -> _liveMessage.postValue(
                    MessageResult.Error(
                        msg = it.throwable.message ?: "Error"
                    )
                )
                MyResult.Loading -> _liveMessage.postValue(MessageResult.Loading(show = true))
                is MyResult.Success -> observeFunction.invoke(it.data)
            }
        }
    }

    fun <T> vmsLaunch(
        block: suspend CoroutineScope.() -> T
    ) = viewModelScope.launch { block() }

}

sealed class MessageResult {
    data class Error(@StringRes val msgRes: Int? = null, val msg: String? = null) : MessageResult()
    data class Loading(
        val show: Boolean,
        @StringRes val msgRes: Int? = null,
        val msg: String = ""
    ) : MessageResult()

    fun getMessage(context: Context): String = when (this) {
        is Error -> if (msgRes != null) context.getString(msgRes) else msg ?: "-"
        is Loading -> if (msgRes == null) msg else context.getString(msgRes)
    }
}