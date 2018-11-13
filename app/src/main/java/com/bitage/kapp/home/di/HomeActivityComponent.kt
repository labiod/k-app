package com.bitage.kapp.home.di

import com.bitage.kapp.home.HomeActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Sub-component for HomeActivityModule
 */
@Subcomponent(modules = [HomeActivityModule::class])
abstract class HomeActivityComponent : AndroidInjector<HomeActivity> {

    @Subcomponent.Builder
    internal abstract class Builder : AndroidInjector.Builder<HomeActivity>() {
        abstract fun plus(module: HomeActivityModule)

        override fun seedInstance(instance: HomeActivity?) {
            instance?.let {
                plus(HomeActivityModule(it))
            }
        }
    }
}