package com.kgb.kapp.di.module

import android.arch.lifecycle.ViewModelProviders
import com.bitage.kapp.repository.TemplateRepository
import com.kgb.kapp.TemplateActivity
import com.kgb.kapp.viewmodel.TemplateViewModel
import com.kgb.kapp.viewmodel.TemplateViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TemplateActivityModule(private val activity: TemplateActivity) {
    @Provides
    fun provideTemplateViewModel(repository: TemplateRepository): TemplateViewModel {
        return ViewModelProviders.of(activity, TemplateViewModelFactory(repository)).get(TemplateViewModel::class.java)
    }
}