package com.bitage.kapp.challenge

import com.bitage.kapp.interactor.ParamLessUseCaseFlowable
import com.bitage.kapp.repository.ChallengeRepository
import io.reactivex.Flowable
import io.reactivex.Scheduler

class RemoveAllChallengeUseCase(
    subscribeScheduler: Scheduler,
    observeScheduler: Scheduler,
    private val repository: ChallengeRepository
) : ParamLessUseCaseFlowable<Boolean>(subscribeScheduler, observeScheduler) {
    override fun buildUseCase(): Flowable<Boolean> {
        return repository.deleteAll()
    }
}