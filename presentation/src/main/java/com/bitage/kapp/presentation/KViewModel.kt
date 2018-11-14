package com.bitage.kapp.presentation

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Base view model class for all model in app. This class composite all disposable
 * to dispose them when view model was onCleared state
 */
abstract class KViewModel : ViewModel() {
    private var compositeDisposable = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) {
        if (compositeDisposable.isDisposed) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable.add(disposable)
    }

    /**
     * Called when view model was cleared
     */
    override fun onCleared() {
        super.onCleared()
        if (compositeDisposable.isDisposed.not()) {
            compositeDisposable.dispose()
        }
    }
}