package com.kgb.kapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kgb.kapp.challenge.ChallengeType
import com.kgb.kapp.challenge.StepProgress
import com.kgb.kapp.db.entity.ChallengeEntity
import com.kgb.kapp.db.entity.UserProgressEntity
import com.kgb.kapp.repository.ChallengesRepository
import java.util.Date
import java.util.concurrent.Executors

/**
 * View model used in [com.kgb.kapp.EditChallengeActivity] class
 * Contains challenge that will be edit
 */
class EditChallengeViewModel(private val repository: ChallengesRepository) : ViewModel() {
    private val executor = Executors.newSingleThreadExecutor()
    private val _challengeProgress = MutableLiveData<UserProgressEntity>()
    /**
     * Getter for _challengeProgress field
     */
    val challengeProgress: LiveData<UserProgressEntity>
        get() = _challengeProgress

    private val _challenge = MutableLiveData<ChallengeEntity>()
    /**
     * Getter for _challenge field
     */
    val challenge: LiveData<ChallengeEntity>
        get() = _challenge

    /**
     * Load challenge for given id and put result to _challenge field
     * If user want to get this data it must observe challenge getter:
     * Example:
     *
     * @param noteId - challenge id
     */
    fun loadChallenge(noteId: Long) {
        executor.execute {
            val note = repository.getChallengeById(noteId)
            _challenge.postValue(note)
        }
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
        repository.update(_challenge.value?.applyChanges(step, progress, goal, series)
            ?: ChallengeEntity(null, challengeType, step, progress, Date(), series, goal))
    }

    /**
     * Load challenge progress for given challenge type
     * @param challengeType - given challenge type
     */
    fun loadChallengeProgress(challengeType: ChallengeType) {
        executor.execute {
            val progress = repository.getChallengeProgress(challengeType)
                ?: UserProgressEntity.createNew(challengeType)
            _challengeProgress.postValue(progress)
        }
    }
}