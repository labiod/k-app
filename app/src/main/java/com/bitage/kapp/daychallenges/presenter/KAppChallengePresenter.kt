package com.bitage.kapp.daychallenges.presenter

import com.bitage.kapp.daychallenges.view.ChallengeListView
import com.bitage.kapp.daychallenges.DayChallengeViewModel
import com.bitage.kapp.daychallenges.view.ChallengeSubView
import com.bitage.kapp.daychallenges.view.ChallengeView
import com.bitage.kapp.model.Challenge

/**
 * Implementation of presenter used to listed challenges for given date
 */
class KAppChallengePresenter(
    private val viewModel: DayChallengeViewModel
) : ChallengePresenter, ChallengeListView.Listener {
    private var view: ChallengeView? = null
    private var subView: ChallengeSubView? = null

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onCreate method
     */
    override fun onCreate() {
    }

    override fun onResume() {
        view?.onResume()
    }

    override fun onPause() {
        view?.onPause()
    }

    override fun attachView(view: ChallengeView) {
        view.onCreate()
        view.attachViewModel(viewModel)
        view.setChallengeActionListener(this)
        this.view = view
    }

    override fun attachFragmentView(subView: ChallengeSubView) {
        view?.attachSubView(subView)
        this.subView = subView
    }

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onDestroy method
     */
    override fun onDestroy() {
        view?.onDestroy()
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
        view?.loadTemplateData()
    }

    override fun onChallengeFinish(challenge: Challenge) {
        viewModel.updateChallenge(challenge)
        viewModel.updateProgress(challenge)
    }

    override fun onChallengeDelete(challenge: Challenge) {
        viewModel.deleteChallenge(challenge)
    }

    override fun onAddChallengeButtonClicked() {

    }
}