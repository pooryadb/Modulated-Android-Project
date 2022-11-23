package ir.pooryadb.xappnamex.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.pooryadb.xappnamex.core.network.retrofit.RetrofitProductApi
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
object NetworkDataSourceModule {

    @Provides
    fun bindsProductDataSource(retrofit: Retrofit): RetrofitProductApi =
        retrofit.create(RetrofitProductApi::class.java)

}
