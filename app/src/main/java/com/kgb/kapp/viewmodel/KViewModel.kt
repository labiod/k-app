package com.kgb.kapp.viewmodel

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class KViewModel : ViewModel() {
    private var compositeDisposable = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) {
        if (compositeDisposable.isDisposed) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        if (compositeDisposable.isDisposed.not()) {
            compositeDisposable.dispose()
        }
    }
}