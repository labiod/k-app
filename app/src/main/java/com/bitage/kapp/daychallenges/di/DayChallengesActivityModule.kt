package com.bitage.kapp.daychallenges.di

import com.bitage.kapp.daychallenges.TodayChallengesActivity
import com.bitage.kapp.presentation.Constants
import com.bitage.kapp.daychallenges.presenter.KAppChallengePresenter
import com.bitage.kapp.daychallenges.view.KappChallengeListView
import com.bitage.kapp.daychallenges.DayChallengeViewModel
import com.bitage.kapp.daychallenges.DayViewModelFactory
import com.bitage.dsl.createDate
import com.bitage.kapp.challenge.GetChallengeByIdUseCase
import com.bitage.kapp.challenge.GetChallengeListUseCase
import com.bitage.kapp.challenge.GetDefaultChallengeTypeValueUseCase
import com.bitage.kapp.challenge.RemoveAllChallengeUseCase
import com.bitage.kapp.challenge.RemoveChallengeUseCase
import com.bitage.kapp.challenge.SetChallengeUseCase
import com.bitage.kapp.daychallenges.presenter.ChallengePresenter
import com.bitage.kapp.daychallenges.view.ChallengeListView
import com.bitage.kapp.dsl.createViewModel
import com.bitage.kapp.template.GetTemplateListUseCase
import com.bitage.kapp.template.LoadFromTemplateUseCase
import com.bitage.kapp.user.UpdateUserProgressUseCase
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
        getChallengeListUseCase: GetChallengeListUseCase,
        getChallengeByIdUseCase: GetChallengeByIdUseCase,
        setChallengeUseCase: SetChallengeUseCase,
        removeChallengeUseCase: RemoveChallengeUseCase,
        removeAllChallengeUseCase: RemoveAllChallengeUseCase,
        getDefaultChallengeTypeValueUseCase: GetDefaultChallengeTypeValueUseCase,
        getTemplateListUseCase: GetTemplateListUseCase,
        updateUserProgressUseCase: UpdateUserProgressUseCase,
        loadFromTemplateUseCase: LoadFromTemplateUseCase
    ): DayChallengeViewModel {
        return createViewModel(activity) {
            factory = DayViewModelFactory(
                getCurrentDate(activity),
                getChallengeListUseCase,
                getChallengeByIdUseCase,
                setChallengeUseCase,
                removeChallengeUseCase,
                removeAllChallengeUseCase,
                getDefaultChallengeTypeValueUseCase,
                getTemplateListUseCase,
                updateUserProgressUseCase,
                loadFromTemplateUseCase
            )
            modelClass = DayChallengeViewModel::class.java
        }
    }

    /**
     * Provide view for challenges list activity
     * @return [KappChallengeListView] instance
     */
    @Provides
    fun provideChallengeListView(): ChallengeListView = KappChallengeListView()

    /**
     * Provide presenter for challenges list activity
     * @param viewModel - instance of view model
     * @return [ChallengeListPresenter] instance
     */
    @Provides
    fun provideChallengeListPresenter(): ChallengePresenter = KAppChallengePresenter()

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