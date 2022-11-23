package ir.pooryadb.xappnamex.core.network.retrofit

import ir.pooryadb.xappnamex.core.network.model.NetworkProduct
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface RetrofitProductApi {

    @GET(value = "products")
    suspend fun getProducts(): Response<NetworkProduct>


    @GET(value = "products/{id}")
    suspend fun getProduct(
        @Path("id") id: Long,
    ): Response<NetworkProduct.Product>

}