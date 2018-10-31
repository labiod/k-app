package com.kgb.kapp.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import com.kgb.kapp.challenge.ChallengeType
import com.kgb.kapp.db.ChallengeRoomDB
import com.kgb.kapp.db.entity.ChallengeEntity
import com.kgb.kapp.db.entity.TemplateEntity
import com.kgb.kapp.db.entity.UserProgressEntity
import com.kgb.kapp.repository.TemplateRepository.Companion.getInstance
import java.util.Calendar
import java.util.Date
import java.util.concurrent.Executors

/**
 * Repository that used [ChallengeRoomDB] to retrieve and manipulate challenges data
 * This class have private constructor and will be create only by [getInstance] method
 */
class DBChallengesRepository private constructor(context: Context) : ChallengesRepository {
    private val db = ChallengeRoomDB.getInstance(context)
    private val executor = Executors.newSingleThreadExecutor()

    /**
     * Get all challenges for given date
     * @param date - given date
     * @return list of challenges for given date
     */
    override fun getDayChallenges(date: Date): LiveData<List<ChallengeEntity>> {
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
        return db.noteDao().getChallengeAtDate(startDate.time, endDate.time)
    }

    /**
     * Delete all challenges
     */
    override fun deleteAll() {
        executor.execute {
            db.noteDao().deleteAll()
        }
    }

    /**
     * Update challenge in database
     * @param challenge - challenge to update
     */
    override fun update(challenge: ChallengeEntity) {
        executor.execute {
            db.noteDao().insertChallenge(challenge)
        }
    }

    /**
     * Update user progress for given challenge
     * @param challenge - given challenge
     */
    override fun updateUserProgress(challenge: ChallengeEntity) {
        executor.execute {
            db.userDao().update(UserProgressEntity(
                challenge.challengeName,
                challenge.step,
                challenge.progress,
                challenge.goal,
                challenge.series
            ))
        }
    }

    /**
     * Delete challenge
     * @param challenge - challenge that will be delete
     */
    override fun deleteChallenge(challenge: ChallengeEntity) {
        executor.execute {
            db.noteDao().deleteChallenge(challenge)
        }
    }

    /**
     * Get challenge for given id
     * @param challengeId - given challenge
     * @return challenge entity
     */
    override fun getChallengeById(challengeId: Long): ChallengeEntity {
        return db.noteDao().getChallengeById(challengeId)
    }

    /**
     * Get list of templates from database
     * @return list ofr templates
     */
    override fun getTemplates(): LiveData<List<TemplateEntity>> {
        return db.templateDao().getAll()
    }

    /**
     * Load data from given template and save them to challenges with given date
     * @param templateEntity - load template
     * @param date - given date
     */
    override fun loadDataFromTemplate(templateEntity: TemplateEntity, date: Date) {
        templateEntity.id?.let {
            executor.execute {
                val challengesType = db.templateDao().loadTemplateType(templateEntity.id)
                val challengesList = ArrayList<ChallengeEntity>()
                for (type in challengesType) {
                    val challengeProgress = db.userDao().getChallengeProgress(type.challengeType)
                        ?: createUserProgress(type.challengeType)
                    challengesList.add(ChallengeEntity(challengeProgress, date))
                }
                db.noteDao().insertAll(challengesList)
            }
        }
    }

    /**
     * Get Challenge progress for given challenge
     * @param challengeType - given challenge
     * @return challenge progress or null if not found
     */
    override fun getChallengeProgress(challengeType: ChallengeType): UserProgressEntity? {
        return db.userDao().getChallengeProgress(challengeType)
    }

    private fun createUserProgress(challengeType: ChallengeType): UserProgressEntity {
        val result = UserProgressEntity.createNew(challengeType)
        db.userDao().update(result)
        return result
    }

    companion object {
        @Volatile
        private var instance: ChallengesRepository? = null
        private val lock = Any()

        /**
         * Get instance of [ChallengesRepository] if instance is null, create new one
         * @param context - context object
         * @return instance of [ChallengesRepository]
         */
        fun getInstance(context: Context): ChallengesRepository {
            return instance?.let { it } ?: createSync(context)
        }

        private fun createSync(context: Context): ChallengesRepository {
            synchronized(lock) {
                return instance ?: run {
                    val result = DBChallengesRepository(context)
                    instance = result
                    result
                }
            }
        }
    }
}