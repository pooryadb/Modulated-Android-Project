package ir.pooryadb.xappnamex.core.data.repository

import ir.pooryadb.xappnamex.core.common.di.AppDispatchers
import ir.pooryadb.xappnamex.core.common.di.Dispatcher
import ir.pooryadb.xappnamex.core.data.model.asModel
import ir.pooryadb.xappnamex.core.data.util.callRetrofit
import ir.pooryadb.xappnamex.core.model.data.Product
import ir.pooryadb.xappnamex.core.network.model.NetworkProduct
import ir.pooryadb.xappnamex.core.network.retrofit.RetrofitProductApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImp @Inject constructor(
    private val productDataSource: RetrofitProductApi,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : ProductRepository {

    override fun getProductsStream(): Flow<List<Product>> = callRetrofit(ioDispatcher) {
        productDataSource.getProducts()
    }.map { it.products.map(NetworkProduct.Product::asModel) }

    override fun getProductStream(id: Long): Flow<Product> = callRetrofit(ioDispatcher) {
        productDataSource.getProduct(id)
    }.map(NetworkProduct.Product::asModel)

}