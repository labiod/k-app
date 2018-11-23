package com.bitage.kapp.home

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.bitage.kapp.presentation.Constants
import com.bitage.kapp.repository.ChallengeRepository
import java.util.Date

/**
 * Factory class for [HomeViewModel] to create view model with challenge repository
 */
class HomeViewModelFactory(private val repository: ChallengeRepository, private val date: Date)
    : ViewModelProvider.NewInstanceFactory() {
    /**
     * Create [HomeViewModel] for given repository
     * @param modelClass - class for created object
     * @return [HomeViewModel] instance
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            return HomeViewModel(repository, date) as T
        } catch (ex: InstantiationException) {
            Log.e(Constants.GLOBAL_TAG, "error:", ex)
            return super.create(modelClass)
        }
    }
}