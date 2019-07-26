package com.bitage.kapp.user

import com.bitage.kapp.interactor.ParamLessUseCaseSingle
import com.bitage.kapp.repository.UserRepository
import io.reactivex.Scheduler
import io.reactivex.Single

class CheckUserInfoUseCase(
    private val userRepository: UserRepository,
    subscribeScheduler: Scheduler,
    observerScheduler: Scheduler
) : ParamLessUseCaseSingle<Boolean>(subscribeScheduler, observerScheduler) {
    override fun buildUseCase(): Single<Boolean> {
        return userRepository.isUserSetup()
    }
}