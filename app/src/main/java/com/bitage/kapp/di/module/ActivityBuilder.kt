package com.bitage.kapp.di.module

import com.bitage.kapp.daychallenges.TodayChallengesActivity
import com.bitage.kapp.daychallenges.di.DayChallengesActivityModule
import com.bitage.kapp.di.ActivityScope
import com.bitage.kapp.editchallenge.EditChallengeActivity
import com.bitage.kapp.editchallenge.di.EditChallengeActivityModule
import com.bitage.kapp.home.HomeActivity
import com.bitage.kapp.home.di.HomeActivityModule
import com.bitage.kapp.template.TemplateActivity
import com.bitage.kapp.template.di.TemplateActivityModule
import com.bitage.kapp.templatelist.TemplateListActivity
import com.bitage.kapp.templatelist.di.TemplateListActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

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
    @ActivityScope
    @ContributesAndroidInjector(modules = [DayChallengesActivityModule::class])
    abstract fun bindDayChallengesActivity(): TodayChallengesActivity

    /**
     * Bind edit challenge activity component to dagger
     * @param builder - builder class used to build dagger component for this activity
     * @return dagger injector factory
     */
    @ActivityScope
    @ContributesAndroidInjector(modules = [EditChallengeActivityModule::class])
    abstract fun bindEditChallengeActivity(): EditChallengeActivity

    /**
     * Bind template activity component to dagger
     * @param builder - builder class used to build dagger component for this activity
     * @return dagger injector factory
     */
    @ActivityScope
    @ContributesAndroidInjector(modules = [TemplateActivityModule::class])
    abstract fun bindTemplateActivity(): TemplateActivity
    /**
     * Bind home activity component to dagger
     * @param builder - builder class used to build dagger component for activity
     * @return dagger injector factory
     */
    @ActivityScope
    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun bindHomeActivity(): HomeActivity

    /**
     * Bind template list activity component to dagger
     * @param builder - builder class used to build dagger component for activity
     * @return dagger injector factory
     */
    @ActivityScope
    @ContributesAndroidInjector(modules = [TemplateListActivityModule::class])
    abstract fun bindTemplateListActivity(): TemplateListActivity
}