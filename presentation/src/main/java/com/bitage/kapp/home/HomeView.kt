package com.bitage.kapp.home

import com.bitage.kapp.presentation.KView

/**
 * View class for home screen
 */
interface HomeView : KView {
    /**
     * Init view with view model
     * @param viewModel - view model for home screen
     */
    fun initModel(viewModel: HomeViewModel)
}