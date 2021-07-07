package com.bitage.kapp.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bitage.kapp.model.Challenge
import com.bitage.kapp.model.UserInfo
import com.bitage.kapp.presentation.KViewModel
import com.bitage.kapp.repository.ChallengeRepository
import com.bitage.kapp.repository.UserRepository
import com.bitage.kapp.user.GetUserInfoUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.launch
import java.util.Date

/**
 * View model for home screen
 */
class HomeViewModel(
    private val challengeRepository: ChallengeRepository,
    private val getUserUseCase: GetUserInfoUseCase,
    date: Date
) : KViewModel() {
    val userInfo: MutableLiveData<UserInfo> = MutableLiveData()
    val dateData: MutableLiveData<Date> = MutableLiveData()
    init {
        dateData.postValue(date)
        getUserUseCase.execute(object : DisposableSubscriber<UserInfo>() {
            override fun onComplete() {}

            override fun onNext(t: UserInfo) {
                userInfo.postValue(t)
            }

            override fun onError(e: Throwable) {
            }
        })
    }

    /**
     * Get pair of value. First of them is actual number of finish challenge and the second is actual
     */
    fun getDayChallengesState(onNext: Consumer<in Pair<Int, Int>>) {
        dateData.value?.let {
            viewModelScope.launch {
                val result = challengeRepository.getDayChallenges(it)
                val pair = Pair(countIfFinish(result), result.size)
                onNext.accept(pair)
            }
        }
    }

    private fun countIfFinish(challenges: List<Challenge>): Int {
        var counter = 0
        challenges.forEach {
            if (it.finished) {
                ++counter
            }
        }
        return counter
    }

    override fun onCleared() {
        super.onCleared()
        getUserUseCase.dispose()
    }
}