package com.bitage.kapp.interactor

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class ParamLessUseCase<ConsumerType> {
    private var compositeDisposable = CompositeDisposable()

    fun execute(consumer: ConsumerType) {
        val disposable = executeWith(consumer)
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

    protected abstract fun executeWith(consumer: ConsumerType): Disposable
}