package com.bitage.kapp.user.di

import androidx.lifecycle.ViewModelProviders
import com.bitage.kapp.user.KAppUserPresenter
import com.bitage.kapp.user.KAppUserView
import com.bitage.kapp.user.UserActivity
import com.bitage.kapp.user.UserPresenter
import com.bitage.kapp.user.UserView
import com.bitage.kapp.user.UserViewModel
import dagger.Module
import dagger.Provides

@Module
class UserActivityModule {
    /**
     * Provide user view model
     * @return instance of [UserViewModel]
     */
    @Provides
    fun provideUserViewModel(
        activity: UserActivity
    ): UserViewModel {
        return ViewModelProviders.of(activity).get(UserViewModel::class.java)
    }

    /**
     * Provide user view
     * @return implementation of [UserView]
     */
    @Provides
    fun provideUserView(activity: UserActivity): UserView = KAppUserView()

    /**
     * Provide user presenter
     * @param model - user model
     * @param view - user view
     * @return implementation of [UserPresenter]
     */
    @Provides
    fun provideUserPresenter(
        model: UserViewModel
    ): UserPresenter {
        return KAppUserPresenter(model)
    }
}