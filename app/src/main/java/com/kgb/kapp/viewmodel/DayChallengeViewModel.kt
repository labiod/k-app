package com.kgb.kapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.kgb.kapp.db.entity.ChallengeEntity
import com.kgb.kapp.db.entity.TemplateEntity
import com.kgb.kapp.repository.ChallengesRepository
import java.text.DateFormat
import java.util.Date

/**
 * View model class used in [com.kgb.kapp.TodayChallengesActivity] class
 * Contains challenges for selected date and list of templates added by user
 */
class DayChallengeViewModel(private val date: Date, private val repository: ChallengesRepository) : ViewModel() {

    private val _challenges = repository.getDayChallenges(date)
    /**
     * Getter for _challenges field
     */
    val challenges: LiveData<List<ChallengeEntity>>
        get() = _challenges

    private val _templates = repository.getTemplates()
    /**
     * Getter for _templates field
     */
    val templates: LiveData<List<TemplateEntity>>
        get() = _templates

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
    fun updateChallenge(item: ChallengeEntity) {
        repository.update(item)
    }

    /**
     * Delete given challenge from repository
     * @param item - item to delete
     */
    fun deleteChallenge(item: ChallengeEntity) {
        repository.deleteChallenge(item)
    }

    /**
     * load given template data to repository for actual date
     * @param templateEntity - template to load
     */
    fun loadDataFromTemplate(templateEntity: TemplateEntity) {
        repository.loadDataFromTemplate(templateEntity, date)
    }

    /**
     * Update progress for given challenge
     * @param item - given challenge
     */
    fun updateProgress(item: ChallengeEntity) {
        repository.updateUserProgress(item)
    }

    fun getDate(formater: DateFormat) = formater.format(date)
}