package com.bitage.kapp.dsl

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bitage.kapp.presentation.KViewModel

@ViewModelProviderDsl
class KViewModelBuilder<T : KViewModel> {
    var factory: ViewModelProvider.NewInstanceFactory? = null
    lateinit var modelClass: Class<T>

    fun build(activity: FragmentActivity): T {
        return factory?.let {
            ViewModelProviders.of(activity, it)
                .get(modelClass)
        } ?: ViewModelProviders.of(activity).get(modelClass)
    }
}