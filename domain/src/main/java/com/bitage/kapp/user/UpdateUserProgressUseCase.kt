package com.bitage.kapp.user

import com.bitage.kapp.challenge.SetChallengeUseCase
import com.bitage.kapp.interactor.UseCaseCompletable
import com.bitage.kapp.model.Challenge
import com.bitage.kapp.repository.ChallengeRepository
import io.reactivex.Completable
import io.reactivex.Scheduler

class UpdateUserProgressUseCase(
    subscribeScheduler: Scheduler,
    observeScheduler: Scheduler,
    private val repository: ChallengeRepository
) : UseCaseCompletable<UpdateUserProgressUseCase.Params>(subscribeScheduler, observeScheduler) {
    override fun buildUseCase(params: Params): Completable {
        return repository.updateUserProgress(params.challenge)
    }

    class Params(val challenge: Challenge)
}