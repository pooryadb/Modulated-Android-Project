package ir.pooryadb.xappnamex.core.common.di

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val appDispatchers: AppDispatchers)

enum class AppDispatchers {
    IO
}
