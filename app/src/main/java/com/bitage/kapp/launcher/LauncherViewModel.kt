package com.bitage.kapp.launcher

import com.bitage.kapp.model.UserInfo
import com.bitage.kapp.presentation.KViewModel
import com.bitage.kapp.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

class LauncherViewModel(private val repository: UserRepository) : KViewModel() {
    fun checkUserSetup(subscriber: Consumer<Boolean>) {
        addDisposable(repository.isUserSetup().subscribe(subscriber))
    }

    fun loadUserInfo(onNext: Consumer<UserInfo>) {
        addDisposable(repository.getUserInfo().subscribe(onNext))
    }

    fun setupUser(userInfo: UserInfo, onComplete: Action) {
        addDisposable(repository.setupUser(userInfo).subscribe(onComplete))
    }
}