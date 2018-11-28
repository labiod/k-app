package com.bitage.kapp.templatelist.di

import com.bitage.kapp.templatelist.TemplateListActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Sub-component for TemplateListActivityModule
 */
@Subcomponent(modules = [TemplateListActivityModule::class])
abstract class TemplateListActivityComponent : AndroidInjector<TemplateListActivity> {

    @Subcomponent.Builder
    internal abstract class Builder : AndroidInjector.Builder<TemplateListActivity>() {
        abstract fun plus(module: TemplateListActivityModule)

        override fun seedInstance(instance: TemplateListActivity?) {
            instance?.let {
                plus(TemplateListActivityModule(it))
            }
        }
    }
}