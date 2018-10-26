package com.kgb.kapp.di.component

import com.kgb.kapp.TodayChallengesActivity
import com.kgb.kapp.di.module.DayChallengesActivityModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [DayChallengesActivityModule::class])
abstract class DayChallengesActivityComponent : AndroidInjector<TodayChallengesActivity> {

    @Subcomponent.Builder
    internal abstract class Builder() : AndroidInjector.Builder<TodayChallengesActivity>() {
        abstract fun plus(module: DayChallengesActivityModule)

        override fun seedInstance(instance: TodayChallengesActivity?) {
            instance?.let {
                plus(DayChallengesActivityModule(it))
            }
        }
    }
}