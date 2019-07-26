package com.bitage.kapp.user

import com.bitage.kapp.interactor.UseCaseCompletable
import com.bitage.kapp.model.UserInfo
import com.bitage.kapp.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.Scheduler

class SetUserInfoUseCase(
    private val userRepository: UserRepository,
    subscribeScheduler: Scheduler,
    observerScheduler: Scheduler
) : UseCaseCompletable<SetUserInfoUseCase.Params>(subscribeScheduler, observerScheduler) {
    override fun buildUseCase(params: Params): Completable {
        return userRepository.setupUser(params.userInfo)
    }

    class Params(val userInfo: UserInfo)
}