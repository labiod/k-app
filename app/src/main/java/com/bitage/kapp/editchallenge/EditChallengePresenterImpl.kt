package com.bitage.kapp.editchallenge

import com.bitage.kapp.model.ChallengeType

/**
 * Implementation of edit challenge presenter
 */
class EditChallengePresenterImpl(
    private val viewModel: EditChallengeViewModel,
    private val view: EditChallengeView,
    val id: Long
) : EditChallengePresenter {

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onCreate method
     */
    override fun onCreate() {
        view.onCreate()
        initViewModel()
        view.initView(viewModel)
    }

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onDestroy method
     */
    override fun onDestroy() {
        view.onDestroy()
    }

    private fun initViewModel() {
        if (id != -1L) {
            viewModel.loadChallenge(id)
        } else {
            viewModel.loadChallengeProgress(ChallengeType.values()[0])
        }
    }
}