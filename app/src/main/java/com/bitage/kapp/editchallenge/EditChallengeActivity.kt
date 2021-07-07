package com.bitage.kapp.editchallenge

import com.bitage.kapp.KActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * This activity is a setting screen for choose challenge
 * It's bind layout R.layout.edit_challenge
 */
class EditChallengeActivity : KActivity<EditChallengeView, EditChallengeViewModel>() {

    /**
     * Presenter for activity
     */
    @Inject
    override lateinit var presenter: EditChallengePresenter

    /**
     * View for activity
     */
    @Inject
    override lateinit var view: EditChallengeView

    /**
     * View for activity
     */
    @Inject
    override lateinit var viewModel: EditChallengeViewModel

    /**
     * Setup dependency injection for this activity
     */
    override fun setupDependencyInjection() {
        AndroidInjection.inject(this)
    }
}