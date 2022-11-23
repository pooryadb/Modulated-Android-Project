package ir.pooryadb.xappnamex.core.data.util

import ir.pooryadb.xappnamex.core.common.data.exceptions.AppThrowable
import ir.pooryadb.xappnamex.core.common.data.exceptions.ThrowableFactory
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException

class RetrofitThrowableFactoryImp<R> : ThrowableFactory<Response<R>> {

    override fun getException(input: Throwable): AppThrowable =
        when {
            input is ConnectException ||
                    input is UnknownHostException ||
                    input is IOException ||
                    (input.message != null && input.message!!.contains("Unable to resolve host")) -> {
                AppThrowable.Connection
            }
            input is CancellationException -> {
                AppThrowable.PagingCanceled
            }
            else -> {
                AppThrowable.UnknownError
            }
        }


    override fun getException(input: Response<R>): AppThrowable =
        when (input.code()) {
            400 -> //bad request > toast all
                AppThrowable.Invalid(input.message())
            in 401..499 -> //unauthorized > logout
                AppThrowable.Unauthorized
            in 500 until 600 -> AppThrowable.Server
            else -> AppThrowable.UnknownError
        }

}