package com.bitage.kapp.daychallenges

import android.view.Menu
import android.view.MenuItem
import com.bitage.kapp.KActivity
import com.bitage.kapp.R
import com.bitage.kapp.daychallenges.presenter.ChallengePresenter
import com.bitage.kapp.daychallenges.view.ChallengeListView
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Activity class that shows challenges for given date
 */
class TodayChallengesActivity : KActivity<ChallengeListView, DayChallengeViewModel>() {

    /**
     * Presenter for this activity
     */
    @Inject
    override lateinit var presenter: ChallengePresenter

    /**
     * View for this activity
     */
    @Inject
    override lateinit var view: ChallengeListView

    /**
     * View for this activity
     */
    @Inject
    override lateinit var viewModel: DayChallengeViewModel

    /**
     * Setup dependency injection for this activity
     */
    override fun setupDependencyInjection() {
        AndroidInjection.inject(this)
    }

    /**
     * Initialize activity menu from R.menu.challenges_menu file
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.challenges_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * Handle all action for item added in [onCreateOptionsMenu] method
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId ?: 0
        return when (id) {
            R.id.action_load_template -> {
                presenter.loadTemplateList()
                true
            }
            R.id.action_delete_all -> {
                presenter.deleteAll()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}