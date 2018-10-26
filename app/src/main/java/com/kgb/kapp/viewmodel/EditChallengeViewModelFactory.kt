package com.kgb.kapp.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.kgb.kapp.challenge.Constants
import com.kgb.kapp.repository.ChallengesRepository

/**
 * Model factory class used to create [EditChallengeViewModel] with given date
 */
class EditChallengeViewModelFactory(private val repository: ChallengesRepository)
    : ViewModelProvider.NewInstanceFactory() {
    /**
     * Create [DayChallengeViewModel] for given date
     * @param modelClass - class for created object
     * @return [DayChallengeViewModel] instance
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            return EditChallengeViewModel(repository) as T
        } catch (ex: InstantiationException) {
            Log.e(Constants.GLOBAL_TAG, "error:", ex)
            return super.create(modelClass)
        }
    }
}