package com.bitage.kapp.editchallenge

import com.bitage.kapp.KActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * This activity is a setting screen for choose challenge
 * It's bind layout R.layout.edit_challenge
 */
class EditChallengeActivity : KActivity<EditChallengeView>() {

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
     * Setup dependency injection for this activity
     */
    override fun setupDependencyInjection() {
        AndroidInjection.inject(this)
    }
}