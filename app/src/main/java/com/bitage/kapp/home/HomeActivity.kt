package com.bitage.kapp.home

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import com.bitage.kapp.KActivity
import com.bitage.kapp.KApplication
import com.bitage.kapp.R
import com.bitage.kapp.templatelist.TemplateListActivity
import com.bitage.kapp.user.UserActivity
import dagger.android.AndroidInjection
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
        AndroidInjection.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_screen_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId ?: 0
        return when (id) {
            R.id.menage_template -> {
                showTemplateScreen()
                true
            }
            R.id.menage_user -> {
                showUserScreen()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun showTemplateScreen() {
        val intent = Intent(this, TemplateListActivity::class.java)
        startActivity(intent)
    }

    private fun showUserScreen() {
        val intent = Intent(this, UserActivity::class.java)
        startActivity(intent)
    }
}