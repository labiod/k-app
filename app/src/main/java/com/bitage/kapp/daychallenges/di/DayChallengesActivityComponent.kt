package com.bitage.kapp.daychallenges.di

import com.bitage.kapp.daychallenges.TodayChallengesActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Sub-component for DayChallengesActivityModule
 */
@Subcomponent(modules = [DayChallengesActivityModule::class])
abstract class DayChallengesActivityComponent : AndroidInjector<TodayChallengesActivity> {

    @Subcomponent.Builder
    internal abstract class Builder : AndroidInjector.Builder<TodayChallengesActivity>() {
        abstract fun plus(module: DayChallengesActivityModule)

        override fun seedInstance(instance: TodayChallengesActivity?) {
            instance?.let {
                plus(DayChallengesActivityModule(it))
            }
        }
    }
}