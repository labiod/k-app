package com.bitage.kapp.templatelist

/**
 * Implementation of presenter for template list screen
 */
class KAppTemplateListPresenter(
    private val model: TemplateListViewModel
) : TemplateListPresenter {
    private lateinit var view: TemplateListView
    override fun onCreate() {
    }

    override fun onDestroy() {
        view.onDestroy()
    }

    override fun attachView(view: TemplateListView) {
        this.view = view
        view.attachViewModel(model)
    }
}