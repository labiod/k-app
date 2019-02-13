package com.bitage.kapp.editchallenge.di

import androidx.lifecycle.ViewModelProviders
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
import java.util.Calendar
import java.util.Date

/**
 * Module for [EditChallengeActivity]. Provide presenter, view model and view for this activity
 */
@Module
class EditChallengeActivityModule {
    /**
     * Provide view model for edit challenge activity
     * @param repository - challenges repository
     * @return [EditChallengeViewModel] object
     */
    @Provides
    fun provideViewModel(
        activity: EditChallengeActivity,
        repository: ChallengeRepository
    ): EditChallengeViewModel {
        return ViewModelProviders
            .of(activity, EditChallengeViewModelFactory(repository, getCurrentDate(activity)))
            .get(EditChallengeViewModel::class.java)
    }

    /**
     * Provide view for edit challenge activity
     * @return [EditChallengeView] instance
     */
    @Provides
    fun provideEditChallengeView(activity: EditChallengeActivity): EditChallengeView {
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
        activity: EditChallengeActivity,
        viewModel: EditChallengeViewModel,
        view: EditChallengeView
    ): EditChallengePresenter {
        val id = activity.intent.getLongExtra(Constants.CHALLENGE_ITEM_ID_KEY, -1)
        return EditChallengePresenterImpl(viewModel, view, id)
    }

    private fun getCurrentDate(activity: EditChallengeActivity): Date {
        return activity.intent?.extras?.let { extras ->
            val day = extras.getInt(Constants.CURRENT_DATE_DAY)
            val month = extras.getInt(Constants.CURRENT_DATE_MONTH)
            val year = extras.getInt(Constants.CURRENT_DATE_YEAR)
            val calendar = Calendar.getInstance()
            calendar.set(year, month, day)
            calendar.time
        } ?: Date()
    }
}