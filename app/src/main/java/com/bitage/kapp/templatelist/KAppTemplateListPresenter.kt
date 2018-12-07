package com.bitage.kapp.templatelist

/**
 * Implementation of presenter for template list screen
 */
class KAppTemplateListPresenter(
    private val model: TemplateListViewModel,
    private val view: TemplateListView
) : TemplateListPresenter {
    override fun onCreate() {
        view.onCreate()
        view.attachViewModel(model)
    }

    override fun onDestroy() {
        view.onDestroy()
    }
}