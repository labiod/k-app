package com.bitage.kapp.daychallenges.presenter

import com.bitage.kapp.daychallenges.OnChallengeActionListener
import com.bitage.kapp.daychallenges.view.ChallengeSubView
import com.bitage.kapp.daychallenges.view.ChallengeView
import com.bitage.kapp.presentation.KFragmentPresenter
import com.bitage.kapp.presentation.KPresenter

/**
 * Presenter used to listed challenges for given date
 */
interface ChallengePresenter : KFragmentPresenter<ChallengeView, ChallengeSubView>, OnChallengeActionListener {
    /**
     * Delete all challenges
     */
    fun deleteAll()

    /**
     * Load list of templates
     */
    fun loadTemplateList()
}