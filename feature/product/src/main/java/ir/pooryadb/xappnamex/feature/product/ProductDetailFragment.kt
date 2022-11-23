package ir.pooryadb.xappnamex.feature.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import ir.pooryadb.xappnamex.core.baseUi.component.BaseFragment
import ir.pooryadb.xappnamex.feature.product.databinding.FragmentProductDetailBinding

class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>() {

    private val args by navArgs<ProductDetailFragmentArgs>()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProductDetailBinding
        get() = FragmentProductDetailBinding::inflate

    override fun FragmentProductDetailBinding.viewHandler(savedInstanceState: Bundle?) {
        toolbarProducts.toolbar.title = args.product.title

        tvDescription.text = args.product.description
        // TODO: load image
//                iv.load(args.product.imageUrl)
    }

}