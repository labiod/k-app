package com.kgb.kapp.repository

import android.arch.lifecycle.LiveData
import com.kgb.kapp.challenge.ChallengeType
import com.kgb.kapp.db.entity.ChallengeEntity
import com.kgb.kapp.db.entity.TemplateEntity
import com.kgb.kapp.db.entity.UserProgressEntity
import java.util.*

/**
 * Repository used to retrieve and manipulate challenges data
 */
interface ChallengesRepository {
    fun getChallengeById(noteId: Long): ChallengeEntity?
    fun update(challengeEntity: ChallengeEntity)
    fun getChallengeProgress(challengeType: ChallengeType): UserProgressEntity?
    fun getDayChallenges(date: Date): LiveData<List<ChallengeEntity>>
    fun getTemplates(): LiveData<List<TemplateEntity>>
    fun deleteAll()
    fun deleteChallenge(item: ChallengeEntity)
    fun loadDataFromTemplate(templateEntity: TemplateEntity, date: Date)
    fun updateUserProgress(item: ChallengeEntity)

}