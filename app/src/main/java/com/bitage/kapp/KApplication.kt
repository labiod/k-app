package com.bitage.kapp

import com.bitage.kapp.di.DaggerApplicationComponent
import com.bitage.kapp.di.DataModule
import com.bitage.kapp.di.RepositoryModule
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerApplication

/**
 * Application class for K-App
 */
open class KApplication : DaggerApplication(), HasActivityInjector {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder()
            .application(this)
            .plus(RepositoryModule(this))
//            .plus(DataModule())
            .build()
    }
}