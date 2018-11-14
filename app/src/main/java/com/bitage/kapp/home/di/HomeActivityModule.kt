package com.bitage.kapp.home.di

import com.bitage.kapp.home.HomePresenter
import com.bitage.kapp.home.HomeActivity
import com.bitage.kapp.home.HomePresenterImpl
import com.bitage.kapp.home.HomeView
import com.bitage.kapp.home.HomeViewImpl
import dagger.Module
import dagger.Provides

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
    fun provideHomeView(): HomeView {
        return HomeViewImpl(activity)
    }

    /**
     * Provide presenter for activity
     * @param view - view for screen
     * @return [HomePresenter] implementation
     */
    @Provides
    fun provideHomePresenter(view: HomeView): HomePresenter {
        return HomePresenterImpl(view)
    }
}