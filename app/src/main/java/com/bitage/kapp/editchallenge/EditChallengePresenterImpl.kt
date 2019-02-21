package com.bitage.kapp.editchallenge

import com.bitage.kapp.model.ChallengeType

/**
 * Implementation of edit challenge presenter
 */
class EditChallengePresenterImpl(
    private val viewModel: EditChallengeViewModel,
    val id: Long
) : EditChallengePresenter {

    private lateinit var view: EditChallengeView

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onCreate method
     */
    override fun onCreate() {
    }

    override fun attachView(view: EditChallengeView) {
        this.view = view
        initViewModel()
    }

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onDestroy method
     */
    override fun onDestroy() {
        view.onDestroy()
    }

    private fun initViewModel() {
        view.attachViewModel(viewModel)
        if (id != -1L) {
            viewModel.loadChallenge(id)
        } else {
            viewModel.loadChallengeProgress(ChallengeType.values()[0])
        }
    }
}