package com.kgb.kapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.kgb.kapp.db.ChallengeEntity
import com.kgb.kapp.db.ChallengesRepository

class TodayChallengeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ChallengesRepository.getInstance(application)
    private val _challenges = repository.challenges
    val challenges : LiveData<List<ChallengeEntity>>
        get() = _challenges

    fun addSampleData() {
       repository.addSampleData()
    }

    fun deleteAll() {
        repository.deleteAll()
    }

    fun updateChallenge(item: ChallengeEntity) {
        repository.update(item)
    }

    fun deleteChallenge(item: ChallengeEntity) {
        repository.deleteChallenge(item)
    }
}