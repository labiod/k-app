package com.bitage.kapp.repository

import com.bitage.kapp.model.UserInfo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface UserRepository {

    fun setupUser(userInfo: UserInfo) : Completable

    fun isUserSetup() : Single<Boolean>

    fun getUserInfo() : Flowable<UserInfo>
}