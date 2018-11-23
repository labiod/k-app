package com.bitage.kapp.editchallenge

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.bitage.kapp.repository.ChallengeRepository
import com.bitage.kapp.presentation.Constants
import java.util.Date

/**
 * Model factory class used to create [EditChallengeViewModel] with given date
 */
class EditChallengeViewModelFactory(
    private val repository: ChallengeRepository,
    private val date: Date
) : ViewModelProvider.NewInstanceFactory() {
    /**
     * Create [EditChallengeViewModel] for given date
     * @param modelClass - class for created object
     * @return [EditChallengeViewModel] instance
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            return EditChallengeViewModel(repository, date) as T
        } catch (ex: InstantiationException) {
            Log.e(Constants.GLOBAL_TAG, "error:", ex)
            return super.create(modelClass)
        }
    }
}