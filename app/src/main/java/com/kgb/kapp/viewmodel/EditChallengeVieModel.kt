package com.kgb.kapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.kgb.kapp.challenge.ChallengeType
import com.kgb.kapp.challenge.StepProgress
import com.kgb.kapp.db.entity.ChallengeEntity
import com.kgb.kapp.db.entity.UserProgressEntity
import com.kgb.kapp.repository.ChallengesRepository
import java.util.*
import java.util.concurrent.Executors

class EditChallengeVieModel(application: Application) : AndroidViewModel(application) {
    private val executor = Executors.newSingleThreadExecutor()
    private val repository = ChallengesRepository.getInstance(application)
    private val _challengeProgress = MutableLiveData<UserProgressEntity>()
    val challengeProgress : LiveData<UserProgressEntity>
        get() = _challengeProgress

    private val _challenge = MutableLiveData<ChallengeEntity>()
    val challenge : LiveData<ChallengeEntity>
        get() = _challenge

    fun loadChallenge(noteId: Long) {
        executor.execute {
            val note = repository.getChallengeById(noteId)
            _challenge.postValue(note)
        }
    }

    fun applyChanges(challengeType: ChallengeType, step: Int, progress: StepProgress, goal: Int, series: Int) {
        repository.update(_challenge.value?.applyChanges(step, progress, goal, series) ?: ChallengeEntity(null, challengeType, step, progress, Date(), series, goal))
    }

    fun loadChallengeProgress(challengeType: ChallengeType) {
        executor.execute {
            val progress = repository.db.userDao().getChallengeProgress(challengeType) ?: UserProgressEntity.createNew(challengeType)
            _challengeProgress.postValue(progress)
        }
    }
}