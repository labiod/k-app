package com.kgb.kapp.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.bitage.kapp.repository.ChallengeRepository
import com.kgb.kapp.challenge.Constants

/**
 * Model factory class used to create [EditChallengeViewModel] with given date
 */
class EditChallengeViewModelFactory(private val repository: ChallengeRepository)
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