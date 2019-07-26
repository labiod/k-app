package com.bitage.kapp.editchallenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bitage.kapp.challenge.GetChallengeByIdUseCase
import com.bitage.kapp.challenge.GetDefaultChallengeTypeValueUseCase
import com.bitage.kapp.challenge.SetChallengeUseCase
import com.bitage.kapp.model.Challenge
import com.bitage.kapp.model.ChallengeType
import com.bitage.kapp.model.StepProgress
import com.bitage.kapp.presentation.KViewModel
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.subscribers.DisposableSubscriber
import java.util.Date

/**
 * View model for edit challenge
 * Contains challenge that will be edit
 */
class EditChallengeViewModel(
    private val getChallengeByIdUseCase: GetChallengeByIdUseCase,
    private val setChallengeUseCase: SetChallengeUseCase,
    private val getDefaultChallengeTypeValueUseCase: GetDefaultChallengeTypeValueUseCase,
    private val date: Date
) : KViewModel() {
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
        getChallengeByIdUseCase.execute(GetChallengeByIdUseCase.Params(noteId),
            object : DisposableSubscriber<Challenge?>() {
                override fun onComplete() {
                }

                override fun onNext(next: Challenge?) {
                    _challenge.postValue(next)
                }

                override fun onError(e: Throwable) {
                }
            }
        )
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
        val params = SetChallengeUseCase.Params(
            _challenge.value?.applyChanges(step, progress, goal, series)
                ?: Challenge(null, challengeType, step, progress, date, series, goal)
        )
        setChallengeUseCase.execute(params, object : DisposableCompletableObserver() {
            override fun onError(e: Throwable) {

            }

            override fun onComplete() {
                _challengeUpdate.postValue(true)
            }
        })
    }

    /**
     * Load challenge progress for given challenge type
     * @param challengeType - given challenge type
     */
    fun loadChallengeProgress(challengeType: ChallengeType) {
        getDefaultChallengeTypeValueUseCase.execute(
            GetDefaultChallengeTypeValueUseCase.Params(challengeType),
            object : DisposableSubscriber<Challenge>() {
                override fun onComplete() {
                }

                override fun onNext(ch: Challenge) {
                    _challengeProgress.postValue(ch)
                }

                override fun onError(t: Throwable?) {
                }
            }
        )
    }
}