package com.bitage.kapp.daychallenges.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bitage.kapp.R
import com.bitage.kapp.Screen
import com.bitage.kapp.databinding.DayChallengesBinding
import com.bitage.kapp.daychallenges.DayChallengeViewModel
import com.bitage.kapp.daychallenges.fragment.ChallengeListFragment
import com.bitage.kapp.daychallenges.fragment.AddOrEditChallengeFragment
import com.bitage.kapp.model.Challenge
import com.bitage.kapp.presentation.Constants

/**
 * List of challenges view implementation class
 */
class KappChallengeListView : ChallengeListView {
    interface Listener {
        fun onAddChallengeButtonClicked()
        fun onEditChallenge(challenge: Challenge)
    }
    private lateinit var binding: DayChallengesBinding
    private lateinit var viewModel: DayChallengeViewModel
    private lateinit var screenHandler: Screen

    private var fragment: Fragment? = null

    /**
     * Controls lifecycle of this view. It should be called in presenter onCreate method
     */
    override fun onCreate() {
    }

    override fun onAttached(screen: Screen) {
        binding = DataBindingUtil.setContentView(screen.getActivity(), R.layout.day_challenges)
        this.screenHandler = screen
    }

    /**
     * Controls lifecycle of this view. It should be called in presenter onDestroy method
     */
    override fun onDestroy() {
        binding.unbind()
    }

    override fun onResume() {
        initBinder()
    }

    override fun onPause() {
    }

    override fun attachFragment(fragment: Fragment) {
        this.fragment = fragment
    }

    /**
     * get the real android view
     */
    override fun androidView(): View = binding.root

    override fun attachViewModel(viewModel: DayChallengeViewModel) {
        this.viewModel = viewModel
    }

    override fun showAddEditChallengeFragment(challengeId: Long?) {
        val fragment = AddOrEditChallengeFragment()
        val argument = Bundle()
        challengeId?.let {
            argument.putLong(Constants.CHALLENGE_ITEM_ID_KEY, it)
            fragment.arguments = argument
        }
        screenHandler
            .getSupportFragmentManager()
            .beginTransaction()
            .add(R.id.container, fragment, "EDIT_CHALLENGE_TAG")
            .addToBackStack("EDIT_CHALLENGE_TAG")
            .commit()
    }

    /**
     * Load list of template
     */
    override fun loadTemplateData() {
        when (val currFragment = fragment) {
            is ChallengeListFragment -> currFragment.loadTemplateData()
        }
    }

    override fun customizeActionBar(actionBar: ActionBar?) {
        actionBar?.let {
            it.setDisplayShowCustomEnabled(true)
            it.setCustomView(R.layout.challenges_action_bar)
            val actionBarView = it.customView
            val title: TextView = actionBarView.findViewById(R.id.action_bar_title)
            val subTitle: TextView = actionBarView.findViewById(R.id.action_bar_subtitle)
            subTitle.text = viewModel.getDate(Constants.APP_DATE_FORMAT)
            title.setText(R.string.your_challenges_title)
        }
    }

    override fun challengeSubmit(success: Boolean) {
        screenHandler.onBackPressed()
    }

    private fun initBinder() {
        screenHandler.getSupportFragmentManager()
            .beginTransaction()
            .add(R.id.container, ChallengeListFragment(), "CHALLENGES_LIST_TAG")
            .commit()
    }
}