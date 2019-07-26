package com.bitage.kapp.challenge

import com.bitage.kapp.interactor.UseCaseCompletable
import com.bitage.kapp.model.Challenge
import com.bitage.kapp.repository.ChallengeRepository
import io.reactivex.Completable
import io.reactivex.Scheduler

class SetChallengeUseCase(
    subscribeScheduler: Scheduler,
    observeScheduler: Scheduler,
    private val repository: ChallengeRepository
) : UseCaseCompletable<SetChallengeUseCase.Params>(subscribeScheduler, observeScheduler) {
    override fun buildUseCase(params: Params): Completable {
        return repository.update(params.challenge)
    }

    class Params(val challenge: Challenge)
}