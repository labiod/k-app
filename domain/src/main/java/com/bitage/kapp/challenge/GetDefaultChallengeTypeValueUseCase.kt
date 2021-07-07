package com.bitage.kapp.challenge

import com.bitage.kapp.interactor.UseCaseFlowable
import com.bitage.kapp.model.Challenge
import com.bitage.kapp.model.ChallengeType
import com.bitage.kapp.repository.ChallengeRepository
import io.reactivex.Flowable
import io.reactivex.Scheduler

class GetDefaultChallengeTypeValueUseCase(
    subscribeScheduler: Scheduler,
    observeScheduler: Scheduler,
    private val repository: ChallengeRepository
) : UseCaseFlowable<GetDefaultChallengeTypeValueUseCase.Params, Challenge>(subscribeScheduler, observeScheduler) {
    override fun buildUseCase(params: Params): Flowable<Challenge> {
        return repository.getDefaultChallengeValues(params.channelType)
    }

    class Params(val channelType: ChallengeType)
}