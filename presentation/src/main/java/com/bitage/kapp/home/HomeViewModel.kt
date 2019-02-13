package com.bitage.kapp.home

import androidx.lifecycle.MutableLiveData
import com.bitage.kapp.model.Challenge
import com.bitage.kapp.presentation.KViewModel
import com.bitage.kapp.repository.ChallengeRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import java.util.Date

/**
 * View model for home screen
 */
class HomeViewModel(private val repository: ChallengeRepository, date: Date) : KViewModel() {
    val dateData: MutableLiveData<Date> = MutableLiveData<Date>()
    init {
        dateData.postValue(date)
    }

    /**
     * Get pair of value. First of them is actual number of finish challenge and the second is actual
     */
    fun getDayChallengesState(onNext: Consumer<in Pair<Int, Int>>) {
        dateData.value?.let {
            val result = repository.getDayChallenges(it)
                .scan(Pair(0, 0)) { _, challenges -> Pair(countIfFinish(challenges), challenges.size) }
            addDisposable(result.observeOn(AndroidSchedulers.mainThread()).subscribe(onNext))
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
}