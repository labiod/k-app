package com.bitage.kapp.launcher.di

import com.bitage.kapp.di.ActivityScope
import com.bitage.kapp.dsl.createViewModel
import com.bitage.kapp.home.HomePresenter
import com.bitage.kapp.launcher.LauncherActivity
import com.bitage.kapp.launcher.LauncherPresenter
import com.bitage.kapp.launcher.LauncherView
import com.bitage.kapp.launcher.LauncherViewModel
import com.bitage.kapp.launcher.LauncherViewModelFactory
import com.bitage.kapp.repository.UserRepository
import com.bitage.kapp.user.CheckUserInfoUseCase
import com.bitage.kapp.user.GetUserInfoUseCase
import com.bitage.kapp.user.SetUserInfoUseCase
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
        getUserInfoUseCase: GetUserInfoUseCase,
        setUserInfoUseCase: SetUserInfoUseCase,
        checkUserInfoUseCase: CheckUserInfoUseCase
    ): LauncherViewModel {
        return createViewModel(activity) {
            factory = LauncherViewModelFactory(getUserInfoUseCase, setUserInfoUseCase, checkUserInfoUseCase)
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
    ): LauncherPresenter = LauncherPresenter()
}