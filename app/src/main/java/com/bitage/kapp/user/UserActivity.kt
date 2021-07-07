package com.bitage.kapp.user

import com.bitage.kapp.KActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class UserActivity : KActivity<UserView, UserViewModel>() {

    /**
     * presenter for template activity
     */
    @Inject
    override lateinit var presenter: UserPresenter

    /**
     * view for template activity
     */
    @Inject
    override lateinit var view: UserView

    /**
     * viewModel for template activity
     */
    @Inject
    override lateinit var viewModel: UserViewModel

    override fun setupDependencyInjection() {
        AndroidInjection.inject(this)
    }
}