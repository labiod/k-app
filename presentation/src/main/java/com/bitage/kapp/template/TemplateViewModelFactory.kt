package com.bitage.kapp.template

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.util.Log
import com.bitage.kapp.presentation.Constants
import com.bitage.kapp.repository.TemplateRepository

/**
 * Model factory class used to create [TemplateViewModel] with given date
 */
class TemplateViewModelFactory(
    private val getTemplateByIdUseCase: GetTemplateByIdUseCase,
    private val setTemplateUseCase: SetTemplateUseCase
)
    : ViewModelProvider.NewInstanceFactory() {
    /**
     * Create [DayChallengeViewModel] for given date
     * @param modelClass - class for created object
     * @return [DayChallengeViewModel] instance
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            return TemplateViewModel(getTemplateByIdUseCase, setTemplateUseCase) as T
        } catch (ex: InstantiationException) {
            Log.e(Constants.GLOBAL_TAG, "error:", ex)
            return super.create(modelClass)
        }
    }
}