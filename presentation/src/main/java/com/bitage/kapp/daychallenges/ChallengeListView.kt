package com.bitage.kapp.daychallenges

import com.bitage.kapp.presentation.KView

/**
 * List of challenges view
 */
interface ChallengeListView : KView {
    /**
     * Init view with viewModel
     * @param viewModel - view model for this view
     */
    fun initView(viewModel: DayChallengeViewModel)

    /**
     * Show 'Create new template' view
     */
    fun showCreateTemplateView()

    /**
     * Load list of template
     */
    fun loadTemplateData()
}