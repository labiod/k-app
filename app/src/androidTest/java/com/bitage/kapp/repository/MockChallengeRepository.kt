package com.bitage.kapp.repository

import com.bitage.kapp.entity.UserProgressEntity
import com.bitage.kapp.mapper.EntityMapper
import com.bitage.kapp.model.Challenge
import com.bitage.kapp.model.ChallengeType
import com.bitage.kapp.model.StepProgress
import io.reactivex.Completable
import io.reactivex.Flowable
import java.util.Date
import kotlin.collections.ArrayList

/**
 * Mock class for ChallengeRepository to test view model
 */
class MockChallengeRepository : ChallengeRepository {
    private val challenges = ArrayList<Challenge>()

    init {
        challenges.add(Challenge(1, ChallengeType.BRIDGE, 1, StepProgress.BEGINNER, Date(), 1, 10))
    }

    override fun getChallengeById(challengeId: Long): Flowable<Challenge?> {
        return Flowable.just(challenges.first { it.id == challengeId })
    }

    override fun getDayChallenges(date: Date): Flowable<List<Challenge>> = Flowable.just(challenges)

    override fun update(challenge: Challenge): Completable = Completable.complete()

    override fun deleteChallenge(challenge: Challenge): Completable = Completable.complete()

    override fun getDefaultChallengeValues(challengeType: ChallengeType): Flowable<Challenge> {
        return Flowable.just(EntityMapper.mapProgressToChallenge(UserProgressEntity.createNew(challengeType)))
    }

    override fun updateUserProgress(challenge: Challenge): Completable = Completable.complete()

    override fun deleteAll(): Flowable<Boolean> = Flowable.just(true)
}