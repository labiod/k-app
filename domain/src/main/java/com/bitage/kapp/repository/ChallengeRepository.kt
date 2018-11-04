package com.bitage.kapp.repository

import com.bitage.kapp.model.Challenge
import com.bitage.kapp.model.ChallengeType
import com.bitage.kapp.model.Template
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import java.util.Date

interface ChallengeRepository {
    /**
     * Get Challenge Entity for give Id
     * @param challengeId - given challenge id
     * @return [Challenge] or null if challenge for given id doesn't exist
     */
    fun getChallengeById(challengeId: Long): Flowable<Challenge?>

    /**
     * Update give challenge entity
     * @param challenge - challenge to update
     */
    fun update(challenge: Challenge): Completable

    /**
     * Get Challenges for given date.
     * @param date - given date
     * @return list of challenges found for given date
     */
    fun getDayChallenges(date: Date): Flowable<List<Challenge>>

    /**
     * Delete all challenges
     */
    fun deleteAll(): Flowable<Boolean>

    /**
     * Delete given challenge
     * @param challenge - challenge to delete
     */
    fun deleteChallenge(challenge: Challenge): Completable

    /**
     * Get Challenge progress for given challenge type
     * @param challengeType - given challenge type
     * @return instance of Challenge with default value for challenge type
     */
    fun getDefaultChallengeValues(challengeType: ChallengeType): Flowable<Challenge>

    /**
     * Update progress for given challenge
     * @param challenge - given challenge
     */
    fun updateUserProgress(challenge: Challenge): Completable
}