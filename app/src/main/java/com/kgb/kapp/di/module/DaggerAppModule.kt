package com.kgb.kapp.di.module

import com.kgb.kapp.TodayChallengesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DaggerAppModule {
    @ContributesAndroidInjector
    internal abstract fun contributeActivityInjector(): TodayChallengesActivity
}