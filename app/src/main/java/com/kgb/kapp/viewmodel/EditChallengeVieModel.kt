package com.kgb.kapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.kgb.kapp.challenge.StepProgress
import com.kgb.kapp.db.ChallengeEntity
import com.kgb.kapp.db.ChallengesRepository
import java.util.concurrent.Executors

class EditChallengeVieModel(application: Application) : AndroidViewModel(application) {
    private val executor = Executors.newSingleThreadExecutor()
    private val repository = ChallengesRepository.getInstance(application)
    private val _challenge = MutableLiveData<ChallengeEntity>()
    val challenge : LiveData<ChallengeEntity>
        get() = _challenge

    fun loadChallenge(noteId: Int) {
        executor.execute {
            val note = repository.getChallengeById(noteId)
            _challenge.postValue(note)
        }
    }

    fun applyChanges(step: Int, progress: StepProgress, goal: Int, series: Int) {
        _challenge.value?.let {
            repository.update(ChallengeEntity(
                it.id,
                it.challengeName,
                step,
                progress,
                it.date,
                series,
                goal,
                it.finished))
        }
    }
}