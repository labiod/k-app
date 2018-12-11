package com.bitage.kapp.daychallenges

import com.bitage.kapp.presentation.KPresenter

/**
 * Presenter used to listed challenges for given date
 */
interface ChallengeListPresenter : KPresenter, OnChallengeActionListener {
    /**
     * Delete all challenges
     */
    fun deleteAll()

    /**
     * Load list of templates
     */
    fun loadTemplateList()
}