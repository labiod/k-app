package com.kgb.kapp.di.component

import com.kgb.kapp.EditChallengeActivity
import com.kgb.kapp.di.module.EditChallengeActivityModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Sub-component for EditChallengeActivityModule
 */
@Subcomponent(modules = [EditChallengeActivityModule::class])
abstract class EditChallengeActivityComponent : AndroidInjector<EditChallengeActivity> {

    @Subcomponent.Builder
    internal abstract class Builder : AndroidInjector.Builder<EditChallengeActivity>() {
        abstract fun plus(module: EditChallengeActivityModule)

        override fun seedInstance(instance: EditChallengeActivity?) {
            instance?.let {
                plus(EditChallengeActivityModule(it))
            }
        }
    }
}