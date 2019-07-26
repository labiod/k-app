package com.bitage.kapp.interactor

import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.subscribers.DisposableSubscriber

abstract class UseCaseFlowable<Params, T>(
    private val subscribeScheduler: Scheduler,
    private val observerScheduler: Scheduler
) : UseCase<Params, DisposableSubscriber<T>>() {

    override fun executeWith(params: Params, consumer: DisposableSubscriber<T>): Disposable {
        return buildUseCase(params)
            .subscribeOn(subscribeScheduler)
            .observeOn(observerScheduler)
            .subscribeWith(consumer)
    }

    protected abstract fun buildUseCase(params: Params) : Flowable<T>
}