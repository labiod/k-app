package com.bitage.kapp.di.module

import com.bitage.kapp.daychallenges.TodayChallengesActivity
import dagger.Module
import android.app.Activity
import com.bitage.kapp.editchallenge.EditChallengeActivity
import com.bitage.kapp.home.HomeActivity
import com.bitage.kapp.template.TemplateActivity
import com.bitage.kapp.daychallenges.di.DayChallengesActivityComponent
import com.bitage.kapp.editchallenge.di.EditChallengeActivityComponent
import com.bitage.kapp.home.di.HomeActivityComponent
import com.bitage.kapp.template.di.TemplateActivityComponent
import dagger.android.AndroidInjector
import dagger.android.ActivityKey
import dagger.multibindings.IntoMap
import dagger.Binds

@Module
abstract class ActivityBuilder {

    @Binds
    @IntoMap
    @ActivityKey(TodayChallengesActivity::class)
    internal abstract fun bindDayChallengesActivity(builder: DayChallengesActivityComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(EditChallengeActivity::class)
    internal abstract fun bindEditChallengeActivity(builder: EditChallengeActivityComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(TemplateActivity::class)
    internal abstract fun bindTemplateActivity(builder: TemplateActivityComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(HomeActivity::class)
    internal abstract fun bindHomeActivity(builder: HomeActivityComponent.Builder): AndroidInjector.Factory<out Activity>
}