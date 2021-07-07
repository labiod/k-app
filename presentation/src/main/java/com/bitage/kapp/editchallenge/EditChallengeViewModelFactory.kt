package com.bitage.kapp.editchallenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.util.Log
import com.bitage.kapp.challenge.GetChallengeByIdUseCase
import com.bitage.kapp.challenge.GetDefaultChallengeTypeValueUseCase
import com.bitage.kapp.challenge.SetChallengeUseCase
import com.bitage.kapp.presentation.Constants
import java.util.Date

/**
 * Model factory class used to create [EditChallengeViewModel] with given date
 */
class EditChallengeViewModelFactory(
    private val getChallengeByIdUseCase: GetChallengeByIdUseCase,
    private val setChallengeUseCase: SetChallengeUseCase,
    private val getDefaultChallengeTypeValueUseCase: GetDefaultChallengeTypeValueUseCase,
    private val date: Date
) : ViewModelProvider.NewInstanceFactory() {
    /**
     * Create [EditChallengeViewModel] for given date
     * @param modelClass - class for created object
     * @return [EditChallengeViewModel] instance
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return try {
            EditChallengeViewModel(getChallengeByIdUseCase, setChallengeUseCase, getDefaultChallengeTypeValueUseCase, date) as T
        } catch (ex: InstantiationException) {
            Log.e(Constants.GLOBAL_TAG, "error:", ex)
            super.create(modelClass)
        }
    }
}