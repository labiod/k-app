package com.bitage.kapp.template

/**
 * Implementation of presenter for template screen
 */
class TemplatePresenterImpl(private val id: Long) : TemplatePresenter {
    private lateinit var view: TemplateView
    private lateinit var viewModel: TemplateViewModel

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

    override fun attachViewModel(viewModel: TemplateViewModel) {
        this.viewModel = viewModel
    }

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onDestroy method
     */
    override fun onDestroy() {
        view.onDestroy()
    }

    private fun initViewModel() {
        view.attachViewModel(viewModel)
        if (id != -1L) {
            viewModel.loadTemplate(id)
        }
    }
}