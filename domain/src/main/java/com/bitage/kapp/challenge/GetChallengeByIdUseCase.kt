package com.bitage.kapp.challenge

import com.bitage.kapp.interactor.UseCaseFlowable
import com.bitage.kapp.model.Challenge
import com.bitage.kapp.repository.ChallengeRepository
import io.reactivex.Flowable
import io.reactivex.Scheduler

class GetChallengeByIdUseCase(
    subscribeScheduler: Scheduler,
    observeScheduler: Scheduler,
    private val repository: ChallengeRepository
) : UseCaseFlowable<GetChallengeByIdUseCase.Params, Challenge?>(subscribeScheduler, observeScheduler) {
    override fun buildUseCase(params: Params): Flowable<Challenge?> {
        return repository.getChallengeById(params.channelId)
    }

    class Params(val channelId: Long)
}