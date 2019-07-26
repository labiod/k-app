package com.bitage.kapp.interactor

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class  UseCase<Params, ConsumerType> {
    private var compositeDisposable = CompositeDisposable()

    fun execute(params: Params, consumer: ConsumerType) {
        val disposable = executeWith(params, consumer)
        if (compositeDisposable.isDisposed) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable.add(disposable)
    }

    fun dispose() {
        if (compositeDisposable.isDisposed.not()) {
            compositeDisposable.dispose()
        }
    }

    protected abstract fun executeWith(params: Params, consumer: ConsumerType): Disposable
}