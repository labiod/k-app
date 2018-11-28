package com.bitage.kapp.presentation

import android.view.View

/**
 * Base view for all non android views.
 */
interface KView<Model : KViewModel> {

    /**
     * Controls lifecycle of this view. It should be called in presenter onCreate method
     */
    fun onCreate()

    /**
     * Controls lifecycle of this view. It should be called in presenter onDestroy method
     */
    fun onDestroy()

    /**
     * get the real android view
     */
    fun androidView(): View

    /**
     * Attach view model to view
     * @param viewModel - instance of view model class extends from [KViewModel]
     */
    fun attachViewModel(viewModel: Model)
}