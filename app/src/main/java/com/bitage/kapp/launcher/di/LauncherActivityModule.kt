package com.bitage.kapp.launcher.di

import androidx.lifecycle.ViewModelProviders
import com.bitage.kapp.di.ActivityScope
import com.bitage.kapp.dsl.createViewModel
import com.bitage.kapp.home.HomePresenter
import com.bitage.kapp.launcher.LauncherActivity
import com.bitage.kapp.launcher.LauncherPresenter
import com.bitage.kapp.launcher.LauncherView
import com.bitage.kapp.launcher.LauncherViewModel
import com.bitage.kapp.launcher.LauncherViewModelFactory
import com.bitage.kapp.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class LauncherActivityModule {
    /**
     * Provide view for activity
     * @return [LauncherView] implementation
     */
    @Provides
    fun provideLauncherView(): LauncherView = LauncherView()

    /**
     * Provide viewModel for activity
     * @param repository - repository for challenges data
     * @return [LauncherViewModel] implementation
     */
    @Provides
    @ActivityScope
    fun provideLauncherViewModel(
        activity: LauncherActivity,
        repository: UserRepository
    ): LauncherViewModel {
        return createViewModel(activity) {
            factory = LauncherViewModelFactory(repository)
            modelClass = LauncherViewModel::class.java
        }
    }

    /**
     * Provide presenter for activity
     * @param view - view for screen
     * @return [LauncherPresenter] implementation
     */
    @Provides
    fun provideLauncherPresenter(
        viewModel: LauncherViewModel
    ): LauncherPresenter = LauncherPresenter(viewModel)
}