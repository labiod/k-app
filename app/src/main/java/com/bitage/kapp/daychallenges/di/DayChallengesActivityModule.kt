package com.bitage.kapp.daychallenges.di

import android.arch.lifecycle.ViewModelProviders
import com.bitage.kapp.repository.ChallengeRepository
import com.bitage.kapp.repository.TemplateRepository
import com.bitage.kapp.daychallenges.TodayChallengesActivity
import com.bitage.kapp.presentation.Constants
import com.bitage.kapp.daychallenges.ChallengeListPresenter
import com.bitage.kapp.daychallenges.ChallengeListPresenterImpl
import com.bitage.kapp.daychallenges.ChallengeListView
import com.bitage.kapp.daychallenges.ChallengeListViewImpl
import com.bitage.kapp.daychallenges.DayChallengeViewModel
import com.bitage.kapp.daychallenges.DayViewModelFactory
import dagger.Module
import dagger.Provides
import java.util.Date
import java.util.Calendar

/**
 * Module for [TodayChallengesActivity] used to inject presenter and view for activity
 */
@Module
class DayChallengesActivityModule {
    /**
     * Provide view model
     * @param repository - repository with challenges data
     * @param templateRepository - repository with template data
     * @return instance of view model
     */
    @Provides
    fun provideViewModel(
        activity: TodayChallengesActivity,
        repository: ChallengeRepository,
        templateRepository: TemplateRepository
    ): DayChallengeViewModel {
        val date = getCurrentDate(activity)
        return ViewModelProviders.of(activity, DayViewModelFactory(date, repository, templateRepository))
            .get(DayChallengeViewModel::class.java)
    }

    /**
     * Provide view for challenges list activity
     * @return [ChallengeListView] instance
     */
    @Provides
    fun provideChallengeListView(activity: TodayChallengesActivity): ChallengeListView {
        return ChallengeListViewImpl(activity)
    }

    /**
     * Provide presenter for challenges list activity
     * @param viewModel - instance of view model
     * @param view - view for challenges list
     * @return [ChallengeListPresenter] instance
     */
    @Provides
    fun provideChallengeListPresenter(
        viewModel: DayChallengeViewModel,
        view: ChallengeListView
    ): ChallengeListPresenter {
        return ChallengeListPresenterImpl(viewModel, view)
    }

    private fun getCurrentDate(activity: TodayChallengesActivity): Date {
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