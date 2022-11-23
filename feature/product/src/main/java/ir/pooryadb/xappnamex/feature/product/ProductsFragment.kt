package ir.pooryadb.xappnamex.feature.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.pooryadb.xappnamex.core.baseUi.component.BaseFragment
import ir.pooryadb.xappnamex.core.baseUi.component.observeMessages
import ir.pooryadb.xappnamex.feature.product.databinding.FragmentProductsBinding
import javax.inject.Inject

@AndroidEntryPoint
class ProductsFragment : BaseFragment<FragmentProductsBinding>() {

    private val productViewModel by viewModels<ProductViewModel>()

    @Inject
    lateinit var productAdapter: ProductAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProductsBinding
        get() = FragmentProductsBinding::inflate

    override fun FragmentProductsBinding.viewHandler(savedInstanceState: Bundle?) {
        toolbarProducts.toolbar.title = findNavController().currentDestination?.label

        rcProducts.adapter = productAdapter

        productAdapter.onClickListener = {
            productViewModel.getProductDetail(it.id)
        }

        if (isRestoredFromBackStack.not())
            productViewModel.getProducts()
    }

    override fun initObservers() {
        super.initObservers()

        observeMessages(productViewModel)

        productViewModel.productsLiveData.observe(this) {
            productAdapter.submitList(it)
        }

        productViewModel.productDetailLiveData.observe(this) {
            findNavController().navigate(
                ProductsFragmentDirections.actionProductsToProductDetail(it)
            )
        }

    }

}