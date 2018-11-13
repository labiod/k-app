package com.bitage.kapp.daychallenges

import com.bitage.kapp.presentation.KPresenter

/**
 * Presenter used to listed challenges for given date
 */
interface ChallengeListPresenter : KPresenter {
    /**
     * Delete all challenges
     */
    fun deleteAll()

    /**
     * Load list of templates
     */
    fun loadTemplateList()

    /**
     * Show the view to create new template
     */
    fun showCreateTemplateView()
}