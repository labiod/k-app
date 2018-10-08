package com.kgb.kapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.kgb.kapp.db.entity.ChallengeEntity
import com.kgb.kapp.db.entity.TemplateEntity
import com.kgb.kapp.repository.ChallengesRepository
import java.util.*

class DayChallengeViewModel(application: Application, val date: Date) : AndroidViewModel(application) {
    private val repository = ChallengesRepository.getInstance(application)
    private val _challenges = repository.getDayChallenges(date)
    val challenges : LiveData<List<ChallengeEntity>>
        get() = _challenges

    private val _templates = repository.getTemplates()
    val templates : LiveData<List<TemplateEntity>>
        get() = _templates

    fun deleteAll() {
        repository.deleteAll()
    }

    fun updateChallenge(item: ChallengeEntity) {
        repository.update(item)
    }

    fun deleteChallenge(item: ChallengeEntity) {
        repository.deleteChallenge(item)
    }

    fun loadDataFromTemplate(templateEntity: TemplateEntity) {
        repository.loadDataFromTemplate(templateEntity, date)
    }
}