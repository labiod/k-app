package com.bitage.kapp.template

/**
 * Implementation of presenter for template screen
 */
class TemplatePresenterImpl(private val model: TemplateViewModel, private val view: TemplateView) : TemplatePresenter {

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onCreate method
     */
    override fun onCreate() {
        view.onCreate()
        view.initView(model)
    }

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onDestroy method
     */
    override fun onDestroy() {
        view.onDestroy()
    }
}