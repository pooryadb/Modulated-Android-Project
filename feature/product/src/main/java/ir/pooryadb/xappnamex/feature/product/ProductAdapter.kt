package ir.pooryadb.xappnamex.feature.product

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.pooryadb.xappnamex.core.model.data.Product
import ir.pooryadb.xappnamex.feature.product.databinding.ItemProductBinding

class ProductAdapter : ListAdapter<Product, ProductAdapter.ProductVH>(DiffCallback()) {

    var onClickListener: ((item: Product) -> Unit)? = null

    private var binding: ItemProductBinding? = null

    override fun onBindViewHolder(holder: ProductVH, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVH {
        binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductVH(binding)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        binding = null
    }

    inner class ProductVH(private val binding: ItemProductBinding?) :
        RecyclerView.ViewHolder(requireNotNull(binding?.root)) {

        fun bind(item: Product) = binding?.apply {
            tvTitle.text = item.title
            tvDescription.text = item.description
            // TODO: load image 
//            ivThumbnail.load(item.thumbnailUrl)

            root.setOnClickListener {
                onClickListener?.invoke(item)
            }
        }

    }

    private class DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem == newItem
        }
    }

}