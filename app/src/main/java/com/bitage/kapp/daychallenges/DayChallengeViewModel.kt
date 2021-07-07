package com.bitage.kapp.daychallenges

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bitage.dsl.format
import com.bitage.kapp.challenge.GetChallengeByIdUseCase
import com.bitage.kapp.challenge.GetChallengeListUseCase
import com.bitage.kapp.challenge.GetDefaultChallengeTypeValueUseCase
import com.bitage.kapp.challenge.RemoveAllChallengeUseCase
import com.bitage.kapp.challenge.RemoveChallengeUseCase
import com.bitage.kapp.challenge.SetChallengeUseCase
import com.bitage.kapp.presentation.KViewModel
import com.bitage.kapp.model.Challenge
import com.bitage.kapp.model.ChallengeType
import com.bitage.kapp.model.StepProgress
import com.bitage.kapp.model.Template
import com.bitage.kapp.template.GetTemplateListUseCase
import com.bitage.kapp.template.LoadFromTemplateUseCase
import com.bitage.kapp.user.UpdateUserProgressUseCase
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.subscribers.DisposableSubscriber
import java.util.Date

/**
 * View model class for day challenges  class
 * Contains challenges for selected date and list of templates added by user
 */
class DayChallengeViewModel(
    private val date: Date,
    private val getChallengeListUseCase: GetChallengeListUseCase,
    private val getChallengeByIdUseCase: GetChallengeByIdUseCase,
    private val setChallengeUseCase: SetChallengeUseCase,
    private val removeChallengeUseCase: RemoveChallengeUseCase,
    private val removeAllChallengeUseCase: RemoveAllChallengeUseCase,
    private val getDefaultChallengeTypeValueUseCase: GetDefaultChallengeTypeValueUseCase,
    private val getTemplateListUseCase: GetTemplateListUseCase,
    private val updateUserProgressUseCase: UpdateUserProgressUseCase,
    private val loadFromTemplateUseCase: LoadFromTemplateUseCase
) : KViewModel() {

    private val _challengeProgress = MutableLiveData<Challenge>()
    private val _challengeUpdate = MutableLiveData<Boolean>()
    private val _challenges = MutableLiveData<List<Challenge>>()

    /**
     * Getter for _challenges field
     */
    val challenges: LiveData<List<Challenge>>
        get() = _challenges

    private val _templates = MutableLiveData<List<Template>>()
    /**
     * Getter for _templates field
     */
    val templates: LiveData<List<Template>>
        get() = _templates

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

    init {
        loadChallenges()
        getTemplateListUseCase.execute(object : DisposableSubscriber<List<Template>>() {
            override fun onComplete() {
            }

            override fun onNext(t: List<Template>?) {
                _templates.postValue(t)
            }

            override fun onError(t: Throwable?) {
            }
        })
    }
    /**
     * Delete all challenges from repository
     */
    fun deleteAll() {
        removeAllChallengeUseCase.execute(object : DisposableSubscriber<Boolean>() {
            override fun onComplete() {}

            override fun onNext(t: Boolean?) {}

            override fun onError(t: Throwable?) {}
        })
    }

    /**
     * Update challenge by given entity in repository
     * @param - item to update
     */
    fun updateChallenge(item: Challenge) {
        setChallengeUseCase.execute(SetChallengeUseCase.Params(item),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    android.util.Log.d("[KGB]", "update challenge")
                }

                override fun onError(e: Throwable) {}
            }
        )
    }

    /**
     * Delete given challenge from repository
     * @param item - item to delete
     */
    fun deleteChallenge(item: Challenge) {
        removeChallengeUseCase.execute(RemoveChallengeUseCase.Params(item),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    loadChallenges()
                }

                override fun onError(e: Throwable) {
                }

            }
        )
    }

    /**
     * load given template data to repository for actual date
     * @param templateEntity - template to load
     */
    fun loadDataFromTemplate(templateEntity: Template) {
        loadFromTemplateUseCase.execute(LoadFromTemplateUseCase.Params(templateEntity, date),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                }
            }
        )
    }

    /**
     * Update progress for given challenge
     * @param item - given challenge
     */
    fun updateProgress(item: Challenge) {
        updateUserProgressUseCase.execute(UpdateUserProgressUseCase.Params(item),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    loadChallenges()
                }

                override fun onError(e: Throwable) {
                }
            }
        )
    }

    /**
     * Get date in given format
     * @param dateformat - date format
     * @return string date
     */
    fun getDate(dateFormat: String) = date format dateFormat

    /**
     * Get date in miliseconds
     * @return time in miliseconds
     */
    fun getTime() = date.time

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
                override fun onComplete() {}

                override fun onNext(t: Challenge?) {
                    _challenge.postValue(t)
                }

                override fun onError(t: Throwable?) {}
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
        val params = SetChallengeUseCase.Params(_challenge.value?.applyChanges(step, progress, goal, series)
            ?: Challenge(null, challengeType, step, progress, date, series, goal))
        setChallengeUseCase.execute(params, object : DisposableCompletableObserver() {
            override fun onComplete() {
                _challengeUpdate.postValue(true)
                loadChallenges()
            }

            override fun onError(e: Throwable) {
            }
        })
    }

    /**
     * Load challenge progress for given challenge type
     * @param challengeType - given challenge type
     */
    fun loadChallengeProgress(challengeType: ChallengeType) {
        getDefaultChallengeTypeValueUseCase.execute(GetDefaultChallengeTypeValueUseCase.Params(challengeType),
            object : DisposableSubscriber<Challenge>() {
                override fun onComplete() {
                }

                override fun onNext(t: Challenge) {
                    _challengeProgress.postValue(t)
                }

                override fun onError(t: Throwable) {}
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        getChallengeByIdUseCase.dispose()
        setChallengeUseCase.dispose()
        removeChallengeUseCase.dispose()
        removeAllChallengeUseCase.dispose()
        getChallengeListUseCase.dispose()
        getDefaultChallengeTypeValueUseCase.dispose()
        getTemplateListUseCase.dispose()
        updateUserProgressUseCase.dispose()
    }

    private fun loadChallenges() {
        getChallengeListUseCase.execute(GetChallengeListUseCase.Params(date),
            object : DisposableSubscriber<List<Challenge>>() {
                override fun onComplete() {}

                override fun onNext(t: List<Challenge>?) {
                    _challenges.postValue(t)
                }

                override fun onError(t: Throwable?) {}
            }
        )
    }
}