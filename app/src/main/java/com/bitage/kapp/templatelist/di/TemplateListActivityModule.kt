package com.bitage.kapp.templatelist.di

import androidx.lifecycle.ViewModelProviders
import com.bitage.kapp.repository.TemplateRepository
import com.bitage.kapp.templatelist.KAppTemplateListPresenter
import com.bitage.kapp.templatelist.KAppTemplateListView
import com.bitage.kapp.templatelist.TemplateListActivity
import com.bitage.kapp.templatelist.TemplateListPresenter
import com.bitage.kapp.templatelist.TemplateListView
import com.bitage.kapp.templatelist.TemplateListViewModel
import com.bitage.kapp.templatelist.TemplateListViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Dagger module for template list screen
 */
@Module
class TemplateListActivityModule {
    /**
     * Provide view model for template list screen
     * @return view model for screen
     */
    @Provides
    fun provideTemplateListViewModel(
        activity: TemplateListActivity,
        repository: TemplateRepository
    ): TemplateListViewModel {
        return ViewModelProviders.of(
            activity,
            TemplateListViewModelFactory(repository)
        ).get(TemplateListViewModel::class.java)
    }

    /**
     * Provide view for template list screen
     * @return implementation of TemplateListView
     */
    @Provides
    fun provideTemplateListView(): TemplateListView = KAppTemplateListView()

    /**
     * Provide presenter for template list screen
     * @param model - view model for screen
     * @return implementation of TemplateListPresenter for template list screen
     */
    @Provides
    fun provideTemplateListPresenter(
        model: TemplateListViewModel
    ): TemplateListPresenter = KAppTemplateListPresenter(model)
}