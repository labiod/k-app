package com.bitage.kapp.daychallenges

import com.bitage.kapp.model.Challenge

/**
 * Implementation of presenter used to listed challenges for given date
 */
class ChallengeListPresenterImpl(
    private val viewModel: DayChallengeViewModel,
    private val view: ChallengeListView
) : ChallengeListPresenter {

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onCreate method
     */
    override fun onCreate() {
        view.onCreate()
        view.attachViewModel(viewModel)
        view.setChallengeActionListener(this)
    }

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onDestroy method
     */
    override fun onDestroy() {
        view.onDestroy()
    }

    /**
     * Delete all challenges from db
     */
    override fun deleteAll() {
        viewModel.deleteAll()
    }

    /**
     * Load list of templates
     */
    override fun loadTemplateList() {
        view.loadTemplateData()
    }

    override fun onChallengeFinish(challenge: Challenge) {
        viewModel.updateChallenge(challenge)
        viewModel.updateProgress(challenge)
    }

    override fun onChallengeDelete(challenge: Challenge) {
        viewModel.deleteChallenge(challenge)
    }
}