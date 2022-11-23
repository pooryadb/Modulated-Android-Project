package ir.pooryadb.xappnamex

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import ir.pooryadb.xappnamex.core.baseUi.utils.language.LocaleUtils

@HiltAndroidApp
class AppCore : Application() {

    override fun onCreate() {
        super.onCreate()

        LocaleUtils.setLocale(this)

        // TODO: restore settings from dataStore
        AppCompatDelegate.setDefaultNightMode(
            AppCompatDelegate.MODE_NIGHT_YES
            /*when (appCache.theme) {
                ThemeState.AUTO_SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                ThemeState.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                ThemeState.DARK -> AppCompatDelegate.MODE_NIGHT_YES
            }*/
        )

    }


    //config language for all application
    override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleUtils.setLocale(this)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}