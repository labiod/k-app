package com.bitage.kapp.interactor

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

abstract class UseCaseObservable<Params, T>(
    private val subscribeScheduler: Scheduler,
    private val observerScheduler: Scheduler
) : UseCase<Params, DisposableObserver<T>>() {

    override fun executeWith(params: Params, consumer: DisposableObserver<T>): Disposable {
        return buildUseCase(params)
            .subscribeOn(subscribeScheduler)
            .observeOn(observerScheduler)
            .subscribeWith(consumer)
    }

    protected abstract fun buildUseCase(params: Params) : Observable<T>
}