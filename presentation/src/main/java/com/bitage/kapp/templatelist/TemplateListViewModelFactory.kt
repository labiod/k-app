package com.bitage.kapp.templatelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.util.Log
import com.bitage.kapp.presentation.Constants
import com.bitage.kapp.repository.TemplateRepository

/**
 * Model factory class used to create [TemplateListViewModel] with given date
 */
class TemplateListViewModelFactory(private val repository: TemplateRepository)
    : ViewModelProvider.NewInstanceFactory() {
    /**
     * Create [TemplateListViewModel] for given date
     * @param modelClass - class for created object
     * @return [TemplateListViewModel] instance
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            return TemplateListViewModel(repository) as T
        } catch (ex: InstantiationException) {
            Log.e(Constants.GLOBAL_TAG, "error:", ex)
            return super.create(modelClass)
        }
    }
}