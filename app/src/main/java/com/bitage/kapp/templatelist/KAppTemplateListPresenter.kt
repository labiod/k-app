package com.bitage.kapp.templatelist

/**
 * Implementation of presenter for template list screen
 */
class KAppTemplateListPresenter : TemplateListPresenter {
    private lateinit var view: TemplateListView
    private lateinit var viewModel: TemplateListViewModel

    override fun onCreate() {}

    override fun onResume() {}

    override fun onPause() {}

    override fun onDestroy() {
        view.onDestroy()
    }

    override fun attachView(view: TemplateListView) {
        this.view = view
        view.attachViewModel(viewModel)
    }

    override fun attachViewModel(viewModel: TemplateListViewModel) {
        this.viewModel = viewModel
    }
}