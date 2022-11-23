package ir.pooryadb.xappnamex.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import ir.pooryadb.xappnamex.R
import ir.pooryadb.xappnamex.core.baseUi.component.BaseActivity
import ir.pooryadb.xappnamex.core.baseUi.ext.toast
import ir.pooryadb.xappnamex.core.common.extention.logListE
import ir.pooryadb.xappnamex.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    private lateinit var navHostFragment: NavHostFragment

    private var doubleBackToExit = false

    private val BACK_EXIT_DELAY_MILLIS = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            false
        }
    }

    override fun viewHandler(savedInstanceState: Bundle?) {
        binding?.apply {
            navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fragMain) as NavHostFragment
            val navController = navHostFragment.navController
            listenNavController(navController)

            /* val appBarConfiguration = AppBarConfiguration(setOf())
             layToolbar.toolbar
                 .setupWithNavController(navController, appBarConfiguration)*/
        }
    }

    override fun onBackPressed() {
        if (navHostFragment.navController.popBackStack().not()) {
            doubleExit(false)
        }
    }

    private fun listenNavController(navController: NavController) {
        navController.addOnDestinationChangedListener { controller, _, _ ->
            controller.backQueue.logListE("$TAG backQueue")
        }
    }

    private fun doubleExit(forceExit: Boolean) {
        if (doubleBackToExit) {
            if (forceExit)
                finishAndRemoveTask()
            else
                super.onBackPressed()

            return
        }

        this.doubleBackToExit = true
        toast(getString(R.string.exit_app))

        binding?.root?.postDelayed({ doubleBackToExit = false }, BACK_EXIT_DELAY_MILLIS)
    }

}