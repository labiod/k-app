package com.bitage.kapp.home

import com.bitage.kapp.KActivity
import com.bitage.kapp.KApplication
import javax.inject.Inject

/**
 * Activity screen to show calendar view
 */
class HomeActivity : KActivity<HomeView>() {

    /**
     * presenter for activity
     */
    @Inject
    override lateinit var presenter: HomePresenter

    /**
     * view for activity
     */
    @Inject
    override lateinit var view: HomeView

    /**
     * Setup dependency injection for this activity
     */
    override fun setupDependencyInjection() {
        KApplication.instance.activityInjector.inject(this)
    }
}