package com.kgb.kapp.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.kgb.kapp.KApplication
import com.kgb.kapp.challenge.Constants
import java.util.Date

/**
 * Model factory class used to create [DayChallengeViewModel] with given date
 */
class DayViewModelFactory(private val challengesDate: Date) : ViewModelProvider.NewInstanceFactory() {
    /**
     * Create [DayChallengeViewModel] for given date
     * @param modelClass - class for created object
     * @return [DayChallengeViewModel] instance
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            return DayChallengeViewModel(KApplication.instance, challengesDate) as T
        } catch (ex: InstantiationException) {
            Log.e(Constants.GLOBAL_TAG, "error:", ex)
            return super.create(modelClass)
        }
    }
}