package com.bitage.kapp

import android.app.Activity
import com.bitage.kapp.di.ApplicationComponent
import com.bitage.kapp.di.DaggerApplicationComponent
import com.bitage.kapp.di.module.RepositoryModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

/**
 * Application class for K-App
 */
open class KApplication : DaggerApplication(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    private lateinit var appComponent: ApplicationComponent
    /**
     * Method call by android when create application
     */
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = getComponent()
        appComponent.inject(this)
        return appComponent
    }

    protected open fun getComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
            .application(this)
            .plus(RepositoryModule(this))
            .build()
    }

    companion object {

        /**
         * Instance of application
         */
        lateinit var instance: KApplication
    }
}