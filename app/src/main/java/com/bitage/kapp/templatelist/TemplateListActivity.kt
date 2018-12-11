package com.bitage.kapp.templatelist

import com.bitage.kapp.KActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Activity with template list
 */
class TemplateListActivity : KActivity<TemplateListView>() {

    /**
     * presenter for template activity
     */
    @Inject
    override lateinit var presenter: TemplateListPresenter

    /**
     * view for template activity
     */
    @Inject
    override lateinit var view: TemplateListView

    override fun setupDependencyInjection() {
        AndroidInjection.inject(this)
    }
}
