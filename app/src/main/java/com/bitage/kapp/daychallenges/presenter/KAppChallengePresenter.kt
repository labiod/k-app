package com.bitage.kapp.daychallenges.presenter

import android.content.Intent
import androidx.fragment.app.Fragment
import com.bitage.kapp.daychallenges.view.KappChallengeListView
import com.bitage.kapp.daychallenges.DayChallengeViewModel
import com.bitage.kapp.daychallenges.fragment.ChallengeListFragment
import com.bitage.kapp.daychallenges.fragment.AddOrEditChallengeFragment
import com.bitage.kapp.daychallenges.view.ChallengeListView
import com.bitage.kapp.editchallenge.EditChallengeActivity
import com.bitage.kapp.model.Challenge
import com.bitage.kapp.presentation.Constants

/**
 * Implementation of presenter used to listed challenges for given date
 */
class KAppChallengePresenter : ChallengePresenter, KappChallengeListView.Listener, AddOrEditChallengeFragment.Listener {
    private var view: ChallengeListView? = null
    private var viewModel: DayChallengeViewModel? = null

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

    override fun attachViewModel(viewModel: DayChallengeViewModel) {
        this.viewModel = viewModel
    }

    override fun attachView(view: ChallengeListView) {
        view.onCreate()
        viewModel?.let {
            view.attachViewModel(it)
        }
        this.view = view
    }

    override fun attachFragment(fragment: Fragment) {
        view?.attachFragment(fragment)
        when(fragment) {
            is AddOrEditChallengeFragment -> fragment.setListener(this)
            is ChallengeListFragment -> fragment.setListener(this)
        }
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
        viewModel?.deleteAll()
    }

    /**
     * Load list of templates
     */
    override fun loadTemplateList() {
        view?.loadTemplateData()
    }

    override fun onAddChallengeButtonClicked() {
        view?.showAddEditChallengeFragment()
    }

    override fun onEditChallenge(challenge: Challenge) {
        view?.showAddEditChallengeFragment(challenge.id)
    }

    override fun onChallengeSubmit(success: Boolean) {
        view?.challengeSubmit(success)
    }
}