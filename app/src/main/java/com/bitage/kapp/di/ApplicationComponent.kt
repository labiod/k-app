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
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun plus(module: RepositoryModule): Builder

        fun build(): ApplicationComponent
    }

    /**
     * Inject application to dagger component
     */
    fun inject(app: KApplication)
}