package com.kgb.kapp.repository

import android.arch.lifecycle.LiveData
import com.kgb.kapp.challenge.ChallengeType
import com.kgb.kapp.db.entity.ChallengeEntity
import com.kgb.kapp.db.entity.TemplateEntity
import com.kgb.kapp.db.entity.UserProgressEntity
import java.util.Date

/**
 * Repository used to retrieve and manipulate challenges data
 */
interface ChallengesRepository {
    /**
     * Get Challenge Entity for give Id
     * @param challengeId - given challenge id
     * @return [ChallengeEntity] or null if challenge for given id doesn't exist
     */
    fun getChallengeById(challengeId: Long): ChallengeEntity?

    /**
     * Update give challenge entity
     * @param challengeEntity - challenge to update
     */
    fun update(challengeEntity: ChallengeEntity)

    /**
     * Get Challenge progress for given challenge type
     * @param challengeType - given challenge type
     * @return [UserProgressEntity] or null if progress for given type doesn't exist yet
     */
    fun getChallengeProgress(challengeType: ChallengeType): UserProgressEntity?

    /**
     * Get Challenges for given date.
     * @param date - given date
     * @return list of challenges found for given date
     */
    fun getDayChallenges(date: Date): LiveData<List<ChallengeEntity>>

    /**
     * Get list of template
     * @return list of template
     */
    fun getTemplates(): LiveData<List<TemplateEntity>>

    /**
     * Delete all challenges
     */
    fun deleteAll()

    /**
     * Delete given challenge
     * @param challenge - challenge to delete
     */
    fun deleteChallenge(challenge: ChallengeEntity)

    /**
     * Load template and create challenges for given template and date
     * @param templateEntity - template for challenges
     * @param date - date for challenges
     */
    fun loadDataFromTemplate(templateEntity: TemplateEntity, date: Date)

    /**
     * Update progress for given challenge
     * @param challenge - given challenge
     */
    fun updateUserProgress(challenge: ChallengeEntity)
}