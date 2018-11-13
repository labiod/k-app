package com.bitage.kapp.di.module

import com.bitage.kapp.daychallenges.TodayChallengesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DaggerAppModule {
    @ContributesAndroidInjector
    internal abstract fun contributeActivityInjector(): TodayChallengesActivity
}