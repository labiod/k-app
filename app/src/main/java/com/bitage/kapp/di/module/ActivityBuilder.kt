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
import com.bitage.kapp.templatelist.TemplateListActivity
import com.bitage.kapp.templatelist.di.TemplateListActivityComponent
import dagger.android.AndroidInjector
import dagger.android.ActivityKey
import dagger.multibindings.IntoMap
import dagger.Binds

/**
 * Dagger module used to bind activity to dagger
 */
@Module
abstract class ActivityBuilder {

    /**
     * Bind today activity component to dagger
     * @param builder - builder class used to build dagger component for this activity
     * @return dagger injector factory
     */
    @Binds
    @IntoMap
    @ActivityKey(TodayChallengesActivity::class)
    internal abstract fun bindDayChallengesActivity(
        builder: DayChallengesActivityComponent.Builder
    ): AndroidInjector.Factory<out Activity>

    /**
     * Bind edit challenge activity component to dagger
     * @param builder - builder class used to build dagger component for this activity
     * @return dagger injector factory
     */
    @Binds
    @IntoMap
    @ActivityKey(EditChallengeActivity::class)
    internal abstract fun bindEditChallengeActivity(
        builder: EditChallengeActivityComponent.Builder
    ): AndroidInjector.Factory<out Activity>

    /**
     * Bind template activity component to dagger
     * @param builder - builder class used to build dagger component for this activity
     * @return dagger injector factory
     */
    @Binds
    @IntoMap
    @ActivityKey(TemplateActivity::class)
    internal abstract fun bindTemplateActivity(
        builder: TemplateActivityComponent.Builder
    ): AndroidInjector.Factory<out Activity>

    /**
     * Bind home activity component to dagger
     * @param builder - builder class used to build dagger component for activity
     * @return dagger injector factory
     */
    @Binds
    @IntoMap
    @ActivityKey(HomeActivity::class)
    internal abstract fun bindHomeActivity(
        builder: HomeActivityComponent.Builder
    ): AndroidInjector.Factory<out Activity>

    /**
     * Bind template list activity component to dagger
     * @param builder - builder class used to build dagger component for activity
     * @return dagger injector factory
     */
    @Binds
    @IntoMap
    @ActivityKey(TemplateListActivity::class)
    internal abstract fun bindTemplateListActivity(
        builder: TemplateListActivityComponent.Builder
    ): AndroidInjector.Factory<out Activity>
}