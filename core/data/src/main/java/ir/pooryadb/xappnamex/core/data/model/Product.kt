package ir.pooryadb.xappnamex.core.data.model

import ir.pooryadb.xappnamex.core.common.D_LONG
import ir.pooryadb.xappnamex.core.common.D_STRING
import ir.pooryadb.xappnamex.core.model.data.Product
import ir.pooryadb.xappnamex.core.network.model.NetworkProduct

fun NetworkProduct.Product.asModel(): Product = Product(
    id = id ?: D_LONG,
    title = title ?: D_STRING,
    description = description ?: D_STRING,
    imageUrl = images?.first() ?: "",
    thumbnailUrl = thumbnail ?: ""
)
