package com.bitage.kapp.di.module

import android.content.Context
import com.bitage.kapp.KApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger application module that provides objects which will live during the application lifecycle.
 */
@Module
class AppModule {
    /**
     * provide application context
     * @param app - application instance
     * @return application context instance
     */
    @Provides
    @Singleton
    fun provideApplication(app: KApplication): Context = app
}