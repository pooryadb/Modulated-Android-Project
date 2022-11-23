package ir.pooryadb.xappnamex.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.pooryadb.xappnamex.core.data.repository.ProductRepository
import ir.pooryadb.xappnamex.core.data.repository.ProductRepositoryImp

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsProductRepository(
        productRepositoryImp: ProductRepositoryImp
    ): ProductRepository

}
