package ir.pooryadb.xappnamex.core.baseUi.component

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import ir.pooryadb.xappnamex.core.baseUi.ext.getAttrColor
import ir.pooryadb.xappnamex.core.baseUi.ext.isDarkTheme
import ir.pooryadb.xappnamex.core.baseUi.utils.language.LocaleUtils
import ir.pooryadb.xappnamex.core.baseUi.utils.state.WindowInsetsHelper
import ir.pooryadb.xappnamex.core.common.extention.logE


abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected open val TAG = this::class.java.simpleName

    internal lateinit var windowInsetsHelper: WindowInsetsHelper

    protected abstract val bindingInflater: (LayoutInflater) -> VB
    protected var binding: VB? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleUtils.setLocale(this)
        resetTitle()

        binding = bindingInflater.invoke(layoutInflater)

        setContentView(requireNotNull(binding).root)

        windowInsetsHelper = WindowInsetsHelper(window, binding?.root)
        configNavigationAndStatusBar()

        viewHandler(savedInstanceState)

        initObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleUtils.setLocale(newBase))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleUtils.setLocale(this)
    }

    protected abstract fun viewHandler(savedInstanceState: Bundle?)
    protected open fun initObservers() {}

    /**
     *  fixes android RTL
     */
    private fun resetTitle() {
        try {
            val label = packageManager.getActivityInfo(
                componentName, PackageManager.GET_META_DATA
            ).labelRes
            if (label != 0) {
                setTitle(label)
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.logE("resetTitle error")
        }
    }

    private fun configNavigationAndStatusBar() {
        window.statusBarColor = getAttrColor(android.R.attr.statusBarColor)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.rootView.windowInsetsController?.let {
                it.setSystemBarsAppearance(
                    if (isDarkTheme()) 0 else WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )

                it.setSystemBarsAppearance(
                    if (isDarkTheme()) 0 else WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
                )

            }
        } else {
            val lightBackground = getAttrColor(android.R.attr.statusBarColor)
            val darkBackground = getAttrColor(android.R.attr.navigationBarColor)

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O_MR1) {
                window.navigationBarColor = darkBackground
            } else {
                window.navigationBarColor = if (isDarkTheme()) darkBackground else lightBackground
            }

            window.statusBarColor = if (isDarkTheme()) darkBackground else lightBackground
        }


    }

}