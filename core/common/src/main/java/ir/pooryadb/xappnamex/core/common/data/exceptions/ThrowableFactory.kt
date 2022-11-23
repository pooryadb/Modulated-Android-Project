package ir.pooryadb.xappnamex.core.common.data.exceptions

interface ThrowableFactory<T> {

    fun getException(input: Throwable): AppThrowable

    fun getException(input: T): AppThrowable

}