package ir.pooryadb.xappnamex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.pooryadb.xappnamex.feature.product.ProductAdapter

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * use [@ApplicationContext] for context
     * e.g.
     *      ... @Inject constructor(
     *          @ApplicationContext context: Context
     *      ) ...
     *
     *
     *      ... @Inject constructor(
     *          application: Application
     *      ) ...
     *
     *
     *      // The Activity binding is available without qualifiers.
     *      ... @Inject constructor(
     *          activity: X-Activity
     *      ) ...
     *
     */

    //fixme: hilt can't process :feature modules!
    @Provides
    fun provideDetailAdapter() = ProductAdapter()

}