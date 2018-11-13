package com.bitage.kapp.editchallenge.di

import android.arch.lifecycle.ViewModelProviders
import com.bitage.kapp.presentation.Constants
import com.bitage.kapp.repository.ChallengeRepository
import com.bitage.kapp.editchallenge.EditChallengeActivity
import com.bitage.kapp.editchallenge.EditChallengePresenter
import com.bitage.kapp.editchallenge.EditChallengePresenterImpl
import com.bitage.kapp.editchallenge.EditChallengeView
import com.bitage.kapp.editchallenge.EditChallengeViewImpl
import com.bitage.kapp.editchallenge.EditChallengeViewModel
import com.bitage.kapp.editchallenge.EditChallengeViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Module for [EditChallengeActivity]. Provide presenter, view model and view for this activity
 */
@Module
class EditChallengeActivityModule(private val activity: EditChallengeActivity) {
    /**
     * Provide view model for edit challenge activity
     * @param repository - challenges repository
     * @return [EditChallengeViewModel] object
     */
    @Provides
    fun provideViewModel(repository: ChallengeRepository): EditChallengeViewModel {
        return ViewModelProviders
            .of(activity, EditChallengeViewModelFactory(repository))
            .get(EditChallengeViewModel::class.java)
    }

    /**
     * Provide view for edit challenge activity
     * @return [EditChallengeView] instance
     */
    @Provides
    fun provideEditChallengeView(): EditChallengeView {
        val editMode = activity.intent.extras?.containsKey(Constants.CHALLENGE_ITEM_ID_KEY) ?: false
        return EditChallengeViewImpl(activity, editMode)
    }

    /**
     * Provide presenter for edit challenge activity
     * @param viewModel - model for activity
     * @param view - view for activity
     * @return [EditChallengePresenter] instance
     */
    @Provides
    fun providerEditChallengePresenter(
        viewModel: EditChallengeViewModel,
        view: EditChallengeView
    ): EditChallengePresenter {
        val id = activity.intent.getLongExtra(Constants.CHALLENGE_ITEM_ID_KEY, -1)
        return EditChallengePresenterImpl(viewModel, view, id)
    }
}