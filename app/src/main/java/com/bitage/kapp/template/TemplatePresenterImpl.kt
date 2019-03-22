package com.bitage.kapp.template

/**
 * Implementation of presenter for template screen
 */
class TemplatePresenterImpl(
    private val model: TemplateViewModel,
    private val id: Long
) : TemplatePresenter {
    private lateinit var view: TemplateView

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onCreate method
     */
    override fun onCreate() {
    }

    override fun onResume() {}

    override fun onPause() {}


    override fun attachView(view: TemplateView) {
        this.view = view
        initViewModel()
    }

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onDestroy method
     */
    override fun onDestroy() {
        view.onDestroy()
    }

    private fun initViewModel() {
        view.attachViewModel(model)
        if (id != -1L) {
            model.loadTemplate(id)
        }
    }
}