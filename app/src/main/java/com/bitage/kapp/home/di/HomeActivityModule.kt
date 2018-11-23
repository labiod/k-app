package com.bitage.kapp.home.di

import android.arch.lifecycle.ViewModelProviders
import com.bitage.kapp.home.HomePresenter
import com.bitage.kapp.home.HomeActivity
import com.bitage.kapp.home.HomePresenterImpl
import com.bitage.kapp.home.HomeView
import com.bitage.kapp.home.HomeViewImpl
import com.bitage.kapp.home.HomeViewModel
import com.bitage.kapp.home.HomeViewModelFactory
import com.bitage.kapp.repository.ChallengeRepository
import dagger.Module
import dagger.Provides
import java.util.Date

/**
 * Dagger module for [HomeActivity]. Provide presenter and view for this activity
 */
@Module
class HomeActivityModule(private val activity: HomeActivity) {
    /**
     * Provide view for activity
     * @return [HomeView] implementation
     */
    @Provides
    fun provideHomeView(): HomeView = HomeViewImpl(activity)

    /**
     * Provide viewModel for activity
     * @param repository - repository for challenges data
     * @return [HomeViewModel] implementation
     */
    @Provides
    fun provideHomeViewModel(repository: ChallengeRepository): HomeViewModel {
        return ViewModelProviders.of(activity, HomeViewModelFactory(repository, Date()))
            .get(HomeViewModel::class.java)
    }

    /**
     * Provide presenter for activity
     * @param view - view for screen
     * @return [HomePresenter] implementation
     */
    @Provides
    fun provideHomePresenter(
        viewModel: HomeViewModel,
        view: HomeView
    ): HomePresenter = HomePresenterImpl(viewModel, view)
}