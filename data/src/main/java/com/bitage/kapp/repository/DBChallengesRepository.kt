package com.bitage.kapp.repository

import com.bitage.kapp.mapper.EntityMapper
import com.bitage.kapp.db.ChallengeDB
import com.bitage.kapp.entity.UserProgressEntity
import com.bitage.kapp.model.Challenge
import com.bitage.kapp.model.ChallengeType
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

/**
 * Repository that used [ChallengeDB] to retrieve and manipulate challenges data
 */
class DBChallengesRepository(private val db: ChallengeDB) : ChallengeRepository {

    /**
     * Get all challenges for given date
     * @param date - given date
     * @return list of challenges for given date
     */
    override fun getDayChallenges(date: Date): Flowable<List<Challenge>> {
        val cal = Calendar.getInstance()
        cal.time = date
        cal.set(Calendar.HOUR, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        val startDate = cal.time
        cal.set(Calendar.HOUR, 23)
        cal.set(Calendar.MINUTE, 59)
        cal.set(Calendar.SECOND, 59)
        cal.set(Calendar.MILLISECOND, 99)
        val endDate = cal.time
        val result: Flowable<List<Challenge>> = Flowable.create( { e ->
            GlobalScope.launch {
                val next = db.noteDao().getChallengeAtDate(startDate.time, endDate.time)
                e.onNext(EntityMapper.mapToChallengeList(next))
            }
        }, BackpressureStrategy.LATEST)
        return result.subscribeOn(Schedulers.io())
    }

    /**
     * Delete all challenges
     */
    override fun deleteAll(): Flowable<Boolean> {
        val result: Flowable<Boolean> = Flowable.create({ e ->
            GlobalScope.launch {
                db.noteDao().deleteAll()
                e.onNext(true)
            }
        }, BackpressureStrategy.LATEST)
        return result.subscribeOn(Schedulers.io())
    }

    /**
     * Update challenge in database
     * @param challenge - challenge to update
     */
    override fun update(challenge: Challenge): Completable {
        android.util.Log.d("[KGB]", "update")
        val result = Completable.create { c ->
            GlobalScope.launch {
                db.noteDao().insertChallenge(EntityMapper.mapToChallengeEntity(challenge))
                c.onComplete()
            }
        }
        return result.subscribeOn(Schedulers.io())
    }

    /**
     * Update user progress for given challenge
     * @param challenge - given challenge
     */
    override fun updateUserProgress(challenge: Challenge): Completable {
        val result: Completable = Completable.create {
            GlobalScope.launch {
                try {
                    db.userDao().update(UserProgressEntity(
                        challenge.challengeName,
                        challenge.step,
                        challenge.progress,
                        challenge.goal,
                        challenge.series
                    ))
                    it.onComplete()
                } catch (ex: Exception) {
                    it.onError(ex)
                }
            }
        }
        return result.subscribeOn(Schedulers.io())
    }

    /**
     * Delete challenge
     * @param challenge - challenge that will be delete
     */
    override fun deleteChallenge(challenge: Challenge): Completable {
        val result: Completable = Completable.create { e ->
            GlobalScope.launch {
                try {
                    db.noteDao().deleteChallenge(EntityMapper.mapToChallengeEntity(challenge))
                    e.onComplete()
                } catch (ex: Exception) {
                    e.onError(ex)
                }
            }
        }

        return result.subscribeOn(Schedulers.io())
    }

    /**
     * Get challenge for given id
     * @param challengeId - given challenge
     * @return challenge or null if doesn't exist for given id
     */
    override fun getChallengeById(challengeId: Long): Flowable<Challenge?> {
        val result: Flowable<Challenge?> = Flowable.create({e ->
            GlobalScope.launch {
                e.onNext(EntityMapper.mapProgressToChallenge(db.noteDao().getChallengeById(challengeId)))
            }

        }, BackpressureStrategy.LATEST)
        return result.subscribeOn(Schedulers.io())
    }

    override fun getDefaultChallengeValues(challengeType: ChallengeType): Flowable<Challenge> {
        val result: Flowable<Challenge> = Flowable.create({e ->
            GlobalScope.launch {
                e.onNext(EntityMapper.mapProgressToChallenge(
                    db.userDao().getChallengeProgress(challengeType)
                        ?: UserProgressEntity.createNew(challengeType)))

            }
        }, BackpressureStrategy.LATEST)
        return result.subscribeOn(Schedulers.io())
    }
}