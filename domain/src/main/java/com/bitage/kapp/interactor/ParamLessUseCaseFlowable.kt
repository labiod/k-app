package com.bitage.kapp.interactor

import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.subscribers.DisposableSubscriber

abstract class ParamLessUseCaseFlowable<T>(
    private val subscribeScheduler: Scheduler,
    private val observerScheduler: Scheduler
) : ParamLessUseCase<DisposableSubscriber<T>>() {

    override fun executeWith(consumer: DisposableSubscriber<T>): Disposable {
        return buildUseCase()
            .subscribeOn(subscribeScheduler)
            .observeOn(observerScheduler)
            .subscribeWith(consumer)
    }

    protected abstract fun buildUseCase() : Flowable<T>
}