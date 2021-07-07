package com.bitage.kapp.templatelist

import com.bitage.kapp.KActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Activity with template list
 */
class TemplateListActivity : KActivity<TemplateListView, TemplateListViewModel>() {

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

    /**
     * viewModel for template activity
     */
    @Inject
    override lateinit var viewModel: TemplateListViewModel

    override fun setupDependencyInjection() {
        AndroidInjection.inject(this)
    }
}
