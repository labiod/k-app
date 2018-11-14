package com.bitage.kapp.editchallenge

import com.bitage.kapp.presentation.KView

/**
 * View for edit challenge screen
 */
interface EditChallengeView : KView {
    /**
     * Init view with view model
     * @param viewModel - view model for screen
     */
    fun initView(viewModel: EditChallengeViewModel)
}