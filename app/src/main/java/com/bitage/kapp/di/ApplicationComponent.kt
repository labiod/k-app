package com.bitage.kapp.di

import android.app.Application
import com.bitage.kapp.KApplication
import com.bitage.kapp.di.module.ActivityBuilder
import com.bitage.kapp.di.module.AppModule
import com.bitage.kapp.di.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

/**
 * Application component for dagger
 */
@Singleton
@Component(modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class,
        RepositoryModule::class
])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {
    /**
     * Builder class for application component
     */
    @Component.Builder
    interface Builder {
        /**
         * Method get builder for application instance
         * @param application - current application instance
         * @return builder for application component
         */
        @BindsInstance
        fun application(application: Application): Builder

        /**
         * Method attach [RepositoryModule] to application component
         * @param module - repository module
         */
        fun plus(module: RepositoryModule): Builder

        /**
         * Build application component
         */
        fun build(): ApplicationComponent
    }

    /**
     * Inject application to dagger component
     */
    fun inject(app: KApplication)
}