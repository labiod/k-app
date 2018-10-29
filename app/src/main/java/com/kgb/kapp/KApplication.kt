package com.kgb.kapp

import android.app.Activity
import com.kgb.kapp.di.component.ApplicationComponent
import com.kgb.kapp.di.component.DaggerApplicationComponent
import com.kgb.kapp.di.module.RepositoryModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

/**
 * Application class for K-App
 */
class KApplication : DaggerApplication(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    lateinit var appComponent: ApplicationComponent
    /**
     * Method call by android when create application
     */
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerApplicationComponent.builder()
                .application(this)
                .plus(RepositoryModule(this))
                .build()
        appComponent.inject(this)
        return appComponent
    }

    companion object {

        /**
         * Instance of application
         */
        lateinit var instance: KApplication
    }
}