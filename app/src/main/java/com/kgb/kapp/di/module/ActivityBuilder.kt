package com.kgb.kapp.di.module

import com.kgb.kapp.TodayChallengesActivity
import dagger.Module
import android.app.Activity
import com.kgb.kapp.EditChallengeActivity
import com.kgb.kapp.TemplateActivity
import com.kgb.kapp.di.component.DayChallengesActivityComponent
import com.kgb.kapp.di.component.EditChallengeActivityComponent
import com.kgb.kapp.di.component.TemplateActivityComponent
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
}