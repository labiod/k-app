package com.bitage.kapp.launcher

import com.bitage.kapp.model.UserInfo
import com.bitage.kapp.presentation.KViewModel
import com.bitage.kapp.repository.UserRepository
import com.bitage.kapp.user.CheckUserInfoUseCase
import com.bitage.kapp.user.GetUserInfoUseCase
import com.bitage.kapp.user.SetUserInfoUseCase
import io.reactivex.Completable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.subscribers.DisposableSubscriber

class LauncherViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val setUserInfoUseCase: SetUserInfoUseCase,
    private val checkUserInfoUseCase: CheckUserInfoUseCase
) : KViewModel() {
    fun checkUserSetup(subscriber: Consumer<Boolean>) {
        checkUserInfoUseCase.execute(object : DisposableSingleObserver<Boolean>() {
            override fun onSuccess(t: Boolean) {
                subscriber.accept(t)
            }

            override fun onError(e: Throwable) {}
        })
    }

    fun loadUserInfo(onNext: Consumer<UserInfo>) {
        getUserInfoUseCase.execute(object : DisposableSubscriber<UserInfo>() {
            override fun onComplete() {
            }

            override fun onNext(t: UserInfo?) {
                onNext.accept(t)
            }

            override fun onError(t: Throwable?) {
            }

        })
    }

    fun setupUser(userInfo: UserInfo, onComplete: Action) {
        setUserInfoUseCase.execute(SetUserInfoUseCase.Params(userInfo),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    onComplete.run()
                }

                override fun onError(e: Throwable) {
                }
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        getUserInfoUseCase.dispose()
    }
}