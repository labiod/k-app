package com.bitage.kapp.daychallenges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.util.Log
import com.bitage.kapp.challenge.GetChallengeByIdUseCase
import com.bitage.kapp.challenge.GetChallengeListUseCase
import com.bitage.kapp.challenge.GetDefaultChallengeTypeValueUseCase
import com.bitage.kapp.challenge.RemoveAllChallengeUseCase
import com.bitage.kapp.challenge.RemoveChallengeUseCase
import com.bitage.kapp.challenge.SetChallengeUseCase
import com.bitage.kapp.repository.ChallengeRepository
import com.bitage.kapp.repository.TemplateRepository
import com.bitage.kapp.presentation.Constants
import com.bitage.kapp.template.GetTemplateListUseCase
import com.bitage.kapp.template.LoadFromTemplateUseCase
import com.bitage.kapp.user.UpdateUserProgressUseCase
import java.util.Date

/**
 * Model factory class used to create [DayChallengeViewModel] with given date
 */
class DayViewModelFactory(
    private val challengesDate: Date,
    private val getChallengeListUseCase: GetChallengeListUseCase,
    private val getChallengeByIdUseCase: GetChallengeByIdUseCase,
    private val setChallengeUseCase: SetChallengeUseCase,
    private val removeChallengeUseCase: RemoveChallengeUseCase,
    private val removeAllChallengeUseCase: RemoveAllChallengeUseCase,
    private val getDefaultChallengeTypeValueUseCase: GetDefaultChallengeTypeValueUseCase,
    private val getTemplateListUseCase: GetTemplateListUseCase,
    private val updateUserProgressUseCase: UpdateUserProgressUseCase,
    private val loadFromTemplateUseCase: LoadFromTemplateUseCase
) : ViewModelProvider.NewInstanceFactory() {
    /**
     * Create [DayChallengeViewModel] for given date
     * @param modelClass - class for created object
     * @return [DayChallengeViewModel] instance
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            return DayChallengeViewModel(
                challengesDate,
                getChallengeListUseCase,
                getChallengeByIdUseCase,
                setChallengeUseCase,
                removeChallengeUseCase,
                removeAllChallengeUseCase,
                getDefaultChallengeTypeValueUseCase,
                getTemplateListUseCase,
                updateUserProgressUseCase,
                loadFromTemplateUseCase
            ) as T
        } catch (ex: InstantiationException) {
            Log.e(Constants.GLOBAL_TAG, "error:", ex)
            return super.create(modelClass)
        }
    }
}