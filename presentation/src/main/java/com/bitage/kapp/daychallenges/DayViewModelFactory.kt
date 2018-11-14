package com.bitage.kapp.daychallenges

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.bitage.kapp.repository.ChallengeRepository
import com.bitage.kapp.repository.TemplateRepository
import com.bitage.kapp.presentation.Constants
import java.util.Date

/**
 * Model factory class used to create [DayChallengeViewModel] with given date
 */
class DayViewModelFactory(
    private val challengesDate: Date,
    private val repository: ChallengeRepository,
    private val templateRepository: TemplateRepository
) : ViewModelProvider.NewInstanceFactory() {
    /**
     * Create [DayChallengeViewModel] for given date
     * @param modelClass - class for created object
     * @return [DayChallengeViewModel] instance
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            return DayChallengeViewModel(challengesDate, repository, templateRepository) as T
        } catch (ex: InstantiationException) {
            Log.e(Constants.GLOBAL_TAG, "error:", ex)
            return super.create(modelClass)
        }
    }
}