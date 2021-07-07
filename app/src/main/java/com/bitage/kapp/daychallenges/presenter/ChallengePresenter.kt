package com.bitage.kapp.daychallenges.presenter

import com.bitage.kapp.daychallenges.DayChallengeViewModel
import com.bitage.kapp.daychallenges.view.ChallengeListView
import com.bitage.kapp.presentation.KPresenter

/**
 * Presenter used to listed challenges for given date
 */
interface ChallengePresenter : KPresenter<ChallengeListView, DayChallengeViewModel> {
    /**
     * Delete all challenges
     */
    fun deleteAll()

    /**
     * Load list of templates
     */
    fun loadTemplateList()
}