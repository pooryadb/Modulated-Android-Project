package ir.pooryadb.xappnamex.core.data.util

import ir.pooryadb.xappnamex.core.common.data.BaseRepository
import ir.pooryadb.xappnamex.core.common.data.exceptions.AppThrowable
import ir.pooryadb.xappnamex.core.common.data.model.MyResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

fun <T> BaseRepository.callRetrofitResult(
    dispatcher: CoroutineDispatcher,
    networkCall: suspend () -> Response<T>
): Flow<MyResult<T>> = flow {
    emit(MyResult.Loading)

    val throwableFactory = RetrofitThrowableFactoryImp<T>()
    try {
        val response = networkCall.invoke()

        if (response.isSuccessful) {
            response.body()?.let {
                emit(MyResult.Success(it))
            } ?: run {
                emit(MyResult.Error(AppThrowable.UnknownError))
            }
        } else {
            emit(MyResult.Error(throwableFactory.getException(response)))
        }
    } catch (e: Exception) {
        emit(MyResult.Error(throwableFactory.getException(e)))
    }

}.flowOn(dispatcher)

fun <T> BaseRepository.callRetrofit(
    dispatcher: CoroutineDispatcher,
    networkCall: suspend () -> Response<T>
): Flow<T> = flow {
    val throwableFactory = RetrofitThrowableFactoryImp<T>()
    try {
        val response = networkCall.invoke()

        if (response.isSuccessful) {
            response.body()?.let {
                emit(it)
            } ?: run {
                throw AppThrowable.UnknownError
            }
        } else {
            throw throwableFactory.getException(response)
        }
    } catch (e: Exception) {
        throw throwableFactory.getException(e)
    }

}.flowOn(dispatcher)