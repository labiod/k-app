package com.bitage.kapp.daychallenges.di

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
import com.bitage.dsl.createDate
import com.bitage.kapp.dsl.createViewModel
import dagger.Module
import dagger.Provides
import java.util.Date

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
        return createViewModel(activity) {
            factory = DayViewModelFactory(getCurrentDate(activity), repository, templateRepository)
            modelClass = DayChallengeViewModel::class.java
        }
    }

    /**
     * Provide view for challenges list activity
     * @return [ChallengeListView] instance
     */
    @Provides
    fun provideChallengeListView(): ChallengeListView = ChallengeListViewImpl()

    /**
     * Provide presenter for challenges list activity
     * @param viewModel - instance of view model
     * @return [ChallengeListPresenter] instance
     */
    @Provides
    fun provideChallengeListPresenter(viewModel: DayChallengeViewModel): ChallengeListPresenter {
        return ChallengeListPresenterImpl(viewModel)
    }

    private fun getCurrentDate(activity: TodayChallengesActivity): Date {
        return activity.intent?.extras?.let {
            createDate {
                day = it.getInt(Constants.CURRENT_DATE_DAY)
                month = it.getInt(Constants.CURRENT_DATE_MONTH)
                year = it.getInt(Constants.CURRENT_DATE_YEAR)
            }
        } ?: Date()
    }
}