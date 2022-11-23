package ir.pooryadb.xappnamex.core.common.data.exceptions

sealed class AppThrowable(val msg: String = "") : Throwable(msg) {

    object Timeout : AppThrowable()
    object Server : AppThrowable()
    object Connection : AppThrowable()
    object PagingCanceled : AppThrowable()
    object UnknownError : AppThrowable()
    object Unauthorized : AppThrowable()

    data class Invalid(val _message: String) : AppThrowable(msg = _message)

}