package com.kgb.kapp.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.util.Log
import com.kgb.kapp.KApplication
import com.kgb.kapp.challenge.Constants
import java.util.*
import kotlin.coroutines.experimental.coroutineContext

class DayViewModelFactory(val challengesDate: Date) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            return DayChallengeViewModel(KApplication.instance, challengesDate) as T
        } catch (ex : InstantiationException) {
            Log.e(Constants.GLOBAL_TAG, "error:", ex)
            return super.create(modelClass)
        }
    }
}