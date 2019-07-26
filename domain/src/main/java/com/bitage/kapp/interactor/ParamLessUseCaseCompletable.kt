package com.bitage.kapp.interactor

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver

abstract class ParamLessUseCaseCompletable(
    private val subscribeScheduler: Scheduler,
    private val observerScheduler: Scheduler
) : ParamLessUseCase<DisposableCompletableObserver>() {

    override fun executeWith(consumer: DisposableCompletableObserver): Disposable {
        return buildUseCase()
            .subscribeOn(subscribeScheduler)
            .observeOn(observerScheduler)
            .subscribeWith(consumer)
    }

    protected abstract fun buildUseCase() : Completable
}