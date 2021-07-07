package com.bitage.kapp.challenge

import com.bitage.kapp.interactor.DispatcherUseCase
import com.bitage.kapp.model.Challenge
import com.bitage.kapp.repository.ChallengeRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Scheduler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date

class GetChallengeListUseCase(
    subscribeScheduler: Scheduler,
    observeScheduler: Scheduler,
    private val repository: ChallengeRepository
) : DispatcherUseCase<GetChallengeListUseCase.Params, List<Challenge>>(subscribeScheduler, observeScheduler) {
    override fun buildUseCase(params: Params): Flowable<List<Challenge>> {
        return Flowable.create({
            GlobalScope.launch {
                it.onNext(repository.getDayChallenges(params.date))
            }
        }, BackpressureStrategy.LATEST)
    }

    class Params(val date: Date)
}