package com.bitage.kapp.template

import com.bitage.kapp.KActivity
import com.bitage.kapp.KApplication
import javax.inject.Inject

/**
 * Activity used to create new template
 */
class TemplateActivity : KActivity<TemplateView>() {

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

    override fun setupDependencyInjection() {
        KApplication.instance.activityInjector.inject(this)
    }
}