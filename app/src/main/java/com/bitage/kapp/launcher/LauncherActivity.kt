package com.bitage.kapp.launcher

import com.bitage.kapp.KActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class LauncherActivity : KActivity<LauncherView, LauncherViewModel>() {
    /**
     * presenter for activity
     */
    @Inject
    override lateinit var presenter: LauncherPresenter

    /**
     * view for activity
     */
    @Inject
    override lateinit var view: LauncherView

    /**
     * view for activity
     */
    @Inject
    override lateinit var viewModel: LauncherViewModel

    override fun setupDependencyInjection() {
        AndroidInjection.inject(this)
    }
}