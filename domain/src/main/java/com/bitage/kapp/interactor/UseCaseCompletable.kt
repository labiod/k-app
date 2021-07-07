package com.bitage.kapp.interactor

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver

abstract class UseCaseCompletable<Params>(
    private val subscribeScheduler: Scheduler,
    private val observerScheduler: Scheduler
) : UseCase<Params, DisposableCompletableObserver>() {

    override fun executeWith(params: Params, consumer: DisposableCompletableObserver): Disposable {
        return buildUseCase(params)
            .subscribeOn(subscribeScheduler)
            .observeOn(observerScheduler)
            .subscribeWith(consumer)
    }

    protected abstract fun buildUseCase(params: Params) : Completable
}