package com.bitage.kapp.template.di

import com.bitage.kapp.template.TemplateActivity
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