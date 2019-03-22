package com.bitage.kapp.launcher

import com.bitage.kapp.KActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class LauncherActivity : KActivity<LauncherView>() {
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

    override fun setupDependencyInjection() {
        AndroidInjection.inject(this)
    }
}