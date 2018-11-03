package com.kgb.kapp.di.component

import com.kgb.kapp.TemplateActivity
import com.kgb.kapp.di.module.TemplateActivityModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Subcomponent for TemplateActivityModule
 */
@Subcomponent(modules = [TemplateActivityModule::class])
abstract class TemplateActivityComponent : AndroidInjector<TemplateActivity> {
    @Subcomponent.Builder
    internal abstract class Builder : AndroidInjector.Builder<TemplateActivity>() {
        abstract fun plus(module: TemplateActivityModule)

        override fun seedInstance(instance: TemplateActivity?) {
            instance?.let {
                plus(TemplateActivityModule(it))
            }
        }
    }
}