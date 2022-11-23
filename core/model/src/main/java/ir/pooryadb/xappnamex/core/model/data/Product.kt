package ir.pooryadb.xappnamex.core.model.data

data class Product(
    val id: Long,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val imageUrl: String,
) : java.io.Serializable

val previewProducts = listOf(
    Product(
        id = 1,
        title = "iPhone 9",
        description = "An apple mobile which is nothing like apple",
        thumbnailUrl = "https://dummyjson.com/image/i/products/1/thumbnail.jpg",
        imageUrl = "https://dummyjson.com/image/i/products/1/1.jpg",
    ),
    Product(
        id = 2,
        title = "iPhone X",
        description = "SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...",
        thumbnailUrl = "https://dummyjson.com/image/i/products/2/thumbnail.jpg",
        imageUrl = "https://dummyjson.com/image/i/products/2/1.jpg",
    )
)
