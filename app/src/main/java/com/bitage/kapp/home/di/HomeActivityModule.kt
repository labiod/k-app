package com.bitage.kapp.home.di

import com.bitage.kapp.di.ActivityScope
import com.bitage.kapp.dsl.createViewModel
import com.bitage.kapp.home.HomePresenter
import com.bitage.kapp.home.HomeActivity
import com.bitage.kapp.home.HomePresenterImpl
import com.bitage.kapp.home.HomeView
import com.bitage.kapp.home.HomeViewImpl
import com.bitage.kapp.home.HomeViewModel
import com.bitage.kapp.home.HomeViewModelFactory
import com.bitage.kapp.repository.ChallengeRepository
import com.bitage.kapp.repository.UserRepository
import com.bitage.kapp.user.GetUserInfoUseCase
import dagger.Module
import dagger.Provides
import java.util.Date

/**
 * Dagger module for [HomeActivity]. Provide presenter and view for this activity
 */
@Module
class HomeActivityModule {
    /**
     * Provide view for activity
     * @return [HomeView] implementation
     */
    @Provides
    fun provideHomeView(): HomeView = HomeViewImpl()

    /**
     * Provide viewModel for activity
     * @param repository - repository for challenges data
     * @param getUserInfoUseCase - usecase for UserInfo
     * @return [HomeViewModel] implementation
     */
    @Provides
    @ActivityScope
    fun provideHomeViewModel(
        activity: HomeActivity,
        repository: ChallengeRepository,
        getUserInfoUseCase: GetUserInfoUseCase
    ): HomeViewModel {
        return createViewModel(activity) {
            factory = HomeViewModelFactory(repository, getUserInfoUseCase, Date())
            modelClass = HomeViewModel::class.java
        }
    }

    /**
     * Provide presenter for activity
     * @param view - view for screen
     * @return [HomePresenter] implementation
     */
    @Provides
    fun provideHomePresenter(): HomePresenter = HomePresenterImpl()
}