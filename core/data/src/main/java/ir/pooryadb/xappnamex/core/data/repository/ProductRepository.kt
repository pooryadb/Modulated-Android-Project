package ir.pooryadb.xappnamex.core.data.repository

import ir.pooryadb.xappnamex.core.common.data.BaseRepository
import ir.pooryadb.xappnamex.core.model.data.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository : BaseRepository {

    fun getProductsStream(): Flow<List<Product>>

    fun getProductStream(id: Long): Flow<Product>

}