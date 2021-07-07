package com.bitage.kapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitage.kapp.interactor.UseCase
import com.bitage.kapp.interactor.UseCaseObservable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

/**
 * Base view model class for all model in app. This class composite all disposable
 * to dispose them when view model was onCleared state
 */
abstract class KViewModel : ViewModel() {}