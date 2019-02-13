package com.bitage.kapp.template.di

import androidx.lifecycle.ViewModelProviders
import com.bitage.kapp.repository.TemplateRepository
import com.bitage.kapp.template.TemplatePresenter
import com.bitage.kapp.template.TemplateViewModel
import com.bitage.kapp.template.TemplateActivity
import com.bitage.kapp.template.TemplatePresenterImpl
import com.bitage.kapp.template.TemplateView
import com.bitage.kapp.template.TemplateViewImpl
import com.bitage.kapp.template.TemplateViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Dagger module for [TemplateActivity] class
 * Provide presenter, view model and view for activity
 */
@Module
class TemplateActivityModule {
    /**
     * Provide template view model
     * @param repository - repository with template data
     * @return instance of [TemplateViewModel]
     */
    @Provides
    fun provideTemplateViewModel(
        activity: TemplateActivity,
        repository: TemplateRepository
    ): TemplateViewModel {
        return ViewModelProviders.of(
            activity,
            TemplateViewModelFactory(repository)
        ).get(TemplateViewModel::class.java)
    }

    /**
     * Provide template view
     * @return implementation of [TemplateView]
     */
    @Provides
    fun provideTemplateView(activity: TemplateActivity): TemplateView = TemplateViewImpl(activity)

    /**
     * Provide template presenter
     * @param model - template model
     * @param view - template view
     * @return implementation of [TemplatePresenter]
     */
    @Provides
    fun providePresenter(
        activity: TemplateActivity,
        model: TemplateViewModel,
        view: TemplateView
    ): TemplatePresenter {
        val id = activity.intent.getLongExtra(TemplateActivity.TEMPLATE_ID_KEY, -1)
        return TemplatePresenterImpl(model, view, id)
    }
}