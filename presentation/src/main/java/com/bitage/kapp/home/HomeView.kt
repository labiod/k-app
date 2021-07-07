package com.bitage.kapp.home

import com.bitage.kapp.model.UserInfo
import com.bitage.kapp.presentation.KView

/**
 * View class for home screen
 */
interface HomeView : KView<HomeViewModel> {
    fun setUserInfo(userInfo: UserInfo)
}