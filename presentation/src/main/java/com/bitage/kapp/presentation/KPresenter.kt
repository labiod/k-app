package com.bitage.kapp.presentation

import androidx.fragment.app.Fragment

/**
 * Base presenter for all presenter class
 */
interface KPresenter<T : KView<*>, Model : KViewModel> {

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onCreate method
     */
    fun onCreate()

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onResume method
     */
    fun onResume()

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onPause method
     */
    fun onPause()

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onDestroy method
     */
    fun onDestroy()

    /**
     * Attach viewModel to presenter
     */
    fun attachViewModel(viewModel: Model)

    /**
     * Attach fragment
     */
    fun attachFragment(fragment: Fragment) {

    }

    /**
     * Attach view to presenter
     */
    fun attachView(view: T)
}