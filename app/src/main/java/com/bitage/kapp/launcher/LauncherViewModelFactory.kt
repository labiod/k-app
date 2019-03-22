package com.bitage.kapp.launcher

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bitage.kapp.annotation.PureFunction
import com.bitage.kapp.home.HomeViewModel
import com.bitage.kapp.presentation.Constants
import com.bitage.kapp.repository.UserRepository

class LauncherViewModelFactory(val repository: UserRepository) : ViewModelProvider.NewInstanceFactory() {
    /**
     * Create [HomeViewModel] for given repository
     * @param modelClass - class for created object
     * @return [HomeViewModel] instance
     */
    @Suppress("UNCHECKED_CAST")
    @PureFunction
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            return LauncherViewModel(repository) as T
        } catch (ex: InstantiationException) {
            Log.e(Constants.GLOBAL_TAG, "error:", ex)
            return super.create(modelClass)
        }
    }
}