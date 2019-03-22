package com.bitage.kapp.editchallenge

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bitage.kapp.presentation.KViewModel
import com.bitage.kapp.model.Challenge
import com.bitage.kapp.model.ChallengeType
import com.bitage.kapp.model.StepProgress
import com.bitage.kapp.repository.ChallengeRepository
import java.util.Date
import java.util.concurrent.Executors

/**
 * View model for edit challenge
 * Contains challenge that will be edit
 */
class EditChallengeViewModel(private val repository: ChallengeRepository, private val date: Date) : KViewModel() {
    private val _challengeProgress = MutableLiveData<Challenge>()
    private val _challengeUpdate = MutableLiveData<Boolean>()
    /**
     * Getter for _challengeProgress field
     */
    val challengeProgress: LiveData<Challenge>
        get() = _challengeProgress

    private val _challenge = MutableLiveData<Challenge>()
    /**
     * Getter for _challenge field
     */
    val challenge: LiveData<Challenge>
        get() = _challenge

    val challengeUpdate: LiveData<Boolean>
        get() = _challengeUpdate

    /**
     * Load challenge for given id and put result to _challenge field
     * If user want to get this data it must observe challenge getter:
     * Example:
     *
     * @param noteId - challenge id
     */
    fun loadChallenge(noteId: Long) {
        val note = repository.getChallengeById(noteId)
        addDisposable(note.subscribe{ next ->
            _challenge.postValue(next)
        })
    }

    /**
     * Update current challenge with given data
     * @param challengeType - challenge type
     * @param step - challenge step
     * @param progress - step progress
     * @param goal - challenge goal
     * @param series - challenge series
     */
    fun applyChanges(challengeType: ChallengeType, step: Int, progress: StepProgress, goal: Int, series: Int) {
        addDisposable(repository.update(_challenge.value?.applyChanges(step, progress, goal, series)
            ?: Challenge(null, challengeType, step, progress, date, series, goal))
            .subscribe {
                _challengeUpdate.postValue(true)
            }
        )
    }

    /**
     * Load challenge progress for given challenge type
     * @param challengeType - given challenge type
     */
    fun loadChallengeProgress(challengeType: ChallengeType) {
        val progress = repository.getDefaultChallengeValues(challengeType)
        addDisposable(progress.subscribe { challenge ->
            _challengeProgress.postValue(challenge)
        })
    }
}