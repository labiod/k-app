package com.bitage.kapp.dsl

import androidx.fragment.app.FragmentActivity
import com.bitage.kapp.presentation.KViewModel

@DslMarker
annotation class ViewModelProviderDsl

@ViewModelProviderDsl
fun<T : KViewModel> createViewModel(activity: FragmentActivity, c: KViewModelBuilder<T>.() -> Unit): T {
    return KViewModelBuilder<T>().apply(c).build(activity)
}