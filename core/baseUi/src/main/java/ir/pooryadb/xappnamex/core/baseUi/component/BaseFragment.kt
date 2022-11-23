package ir.pooryadb.xappnamex.core.baseUi.component

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import ir.pooryadb.xappnamex.core.baseUi.state.AppFragment
import ir.pooryadb.xappnamex.core.baseUi.utils.state.AppFragmentEnum

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    protected lateinit var activityContext: BaseActivity<*>

    protected var binding: VB? = null
        private set
    protected abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    protected var isRestoredFromBackStack = false

    protected open val fragmentEnum: AppFragment = AppFragmentEnum.DEFAULT

    protected open val TAG = this::class.java.simpleName

    protected abstract fun VB.viewHandler(savedInstanceState: Bundle?)
    protected open fun initObservers() {}
    protected open fun initBackStackObservers() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isRestoredFromBackStack = false
        initBackStackObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        isRestoredFromBackStack = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingInflater.invoke(inflater, container, false)

        return requireNotNull(binding).root
    }

    override fun onResume() {
        super.onResume()

        handleFragEnum()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewHandler(savedInstanceState)
        initObservers()
    }

    protected fun loadingDialog(show: Boolean = true) {
        if (show) dialogLoading.show() else dialogLoading.dismiss()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context as BaseActivity<*>
    }

    private val dialogLoading: LoadingDialog by lazy { LoadingDialog(requireContext()) }


    private fun handleFragEnum() = activityContext.apply {
        windowInsetsHelper.isFullScreen = fragmentEnum.isFullScreen()
        windowInsetsHelper.isAutoResizeKeyboard = fragmentEnum.resizeInputMode()
    }

}