package com.bitage.kapp.daychallenges

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bitage.dsl.format
import com.bitage.kapp.presentation.KViewModel
import com.bitage.kapp.model.Challenge
import com.bitage.kapp.model.Template
import com.bitage.kapp.repository.ChallengeRepository
import com.bitage.kapp.repository.TemplateRepository
import java.util.Date

/**
 * View model class for day challenges  class
 * Contains challenges for selected date and list of templates added by user
 */
class DayChallengeViewModel(private val date: Date,
                            private val repository: ChallengeRepository,
                            private val templateRepository: TemplateRepository) : KViewModel() {

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

    init {
        addDisposable(repository.getDayChallenges(date).subscribe({
            _challenges.postValue(it)
        }) {
            error(it)
        })
        addDisposable(templateRepository.getTemplates().subscribe({
            _templates.postValue(it)
        }) {
            error(it)
        })
    }
    /**
     * Delete all challenges from repository
     */
    fun deleteAll() {
        repository.deleteAll()
    }

    /**
     * Update challenge by given entity in repository
     * @param - item to update
     */
     internal fun updateChallenge(item: Challenge) {
        repository.update(item)
    }

    /**
     * Delete given challenge from repository
     * @param item - item to delete
     */
    internal fun deleteChallenge(item: Challenge) {
        addDisposable(repository.deleteChallenge(item)
            .subscribe {
                repository.getDayChallenges(date)
            })
    }

    /**
     * load given template data to repository for actual date
     * @param templateEntity - template to load
     */
    fun loadDataFromTemplate(templateEntity: Template) {
        templateRepository.loadDataFromTemplate(templateEntity, date)
    }

    /**
     * Update progress for given challenge
     * @param item - given challenge
     */
    internal fun updateProgress(item: Challenge) {
        addDisposable(repository.updateUserProgress(item).subscribe {
            repository.getDayChallenges(date)
        })
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
}