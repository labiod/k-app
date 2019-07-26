package com.bitage.kapp.user

import com.bitage.kapp.interactor.ParamLessUseCaseFlowable
import com.bitage.kapp.model.UserInfo
import com.bitage.kapp.repository.UserRepository
import io.reactivex.Flowable
import io.reactivex.Scheduler

class GetUserInfoUseCase(
    private val userRepository: UserRepository,
    subscribeScheduler: Scheduler,
    observerScheduler: Scheduler
) : ParamLessUseCaseFlowable<UserInfo>(subscribeScheduler, observerScheduler) {
    override fun buildUseCase(): Flowable<UserInfo> {
        return userRepository.getUserInfo()
    }
}