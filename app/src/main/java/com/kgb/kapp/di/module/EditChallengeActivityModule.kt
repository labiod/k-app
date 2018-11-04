package com.kgb.kapp.di.module

import android.arch.lifecycle.ViewModelProviders
import com.bitage.kapp.repository.ChallengeRepository
import com.kgb.kapp.EditChallengeActivity
import com.kgb.kapp.viewmodel.EditChallengeViewModel
import com.kgb.kapp.viewmodel.EditChallengeViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class EditChallengeActivityModule(private val activity: EditChallengeActivity) {
    @Provides
    fun provideViewModel(repository: ChallengeRepository): EditChallengeViewModel {
        return ViewModelProviders.of(activity, EditChallengeViewModelFactory(repository)).get(EditChallengeViewModel::class.java)
    }
}