package ir.pooryadb.xappnamex.core.network.model

import com.google.gson.annotations.SerializedName

data class NetworkProduct(
    @SerializedName("limit")
    val limit: Int? = null,
    @SerializedName("products")
    val products: List<Product> = emptyList(),
    @SerializedName("skip")
    val skip: Int? = null,
    @SerializedName("total")
    val total: Int? = null
) {
    data class Product(
        @SerializedName("brand")
        val brand: String? = null,
        @SerializedName("category")
        val category: String? = null,
        @SerializedName("description")
        val description: String? = null,
        @SerializedName("discountPercentage")
        val discountPercentage: Double? = null,
        @SerializedName("id")
        val id: Long? = null,
        @SerializedName("images")
        val images: List<String?>? = null,
        @SerializedName("price")
        val price: Int? = null,
        @SerializedName("rating")
        val rating: Double? = null,
        @SerializedName("stock")
        val stock: Int? = null,
        @SerializedName("thumbnail")
        val thumbnail: String? = null,
        @SerializedName("title")
        val title: String? = null
    )
}