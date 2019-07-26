package com.bitage.kapp.interactor

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver

abstract class ParamLessUseCaseSingle<T>(
    private val subscribeScheduler: Scheduler,
    private val observerScheduler: Scheduler
) : ParamLessUseCase<DisposableSingleObserver<T>>() {

    override fun executeWith(consumer: DisposableSingleObserver<T>): Disposable {
        return buildUseCase()
            .subscribeOn(subscribeScheduler)
            .observeOn(observerScheduler)
            .subscribeWith(consumer)
    }

    protected abstract fun buildUseCase() : Single<T>
}