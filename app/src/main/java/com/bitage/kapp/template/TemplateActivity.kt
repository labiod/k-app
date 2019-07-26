package com.bitage.kapp.template

import com.bitage.kapp.KActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Activity used to create new template
 */
class TemplateActivity : KActivity<TemplateView, TemplateViewModel>() {

    /**
     * presenter for template activity
     */
    @Inject
    override lateinit var presenter: TemplatePresenter

    /**
     * view for template activity
     */
    @Inject
    override lateinit var view: TemplateView

    /**
     * view for template activity
     */
    @Inject
    override lateinit var viewModel: TemplateViewModel

    override fun setupDependencyInjection() {
        AndroidInjection.inject(this)
    }

    companion object {
        const val TEMPLATE_ID_KEY = "template_id_tag"
    }
}