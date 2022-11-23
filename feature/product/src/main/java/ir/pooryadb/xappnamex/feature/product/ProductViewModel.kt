package ir.pooryadb.xappnamex.feature.product

import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.pooryadb.xappnamex.core.baseUi.BaseViewModel
import ir.pooryadb.xappnamex.core.baseUi.utils.liveData.SingleLiveData
import ir.pooryadb.xappnamex.core.data.repository.ProductRepository
import ir.pooryadb.xappnamex.core.model.data.Product
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel() {

    private val _productsLiveData = SingleLiveData<List<Product>>()
    val productsLiveData: LiveData<List<Product>>
        get() = _productsLiveData

    private val _productDetailLiveData = SingleLiveData<Product>()
    val productDetailLiveData: LiveData<Product>
        get() = _productDetailLiveData

    fun getProducts() = observeResult(productRepository.getProductsStream()) {
        _productsLiveData.postValue(it)
    }

    fun getProductDetail(id: Long) = observeResult(productRepository.getProductStream(id)) {
        _productDetailLiveData.postValue(it)
    }

}