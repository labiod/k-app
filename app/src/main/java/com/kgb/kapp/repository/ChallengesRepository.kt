package com.kgb.kapp.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import com.kgb.kapp.challenge.ChallengeType
import com.kgb.kapp.challenge.StepProgress
import com.kgb.kapp.db.entity.ChallengeEntity
import com.kgb.kapp.db.ChallengeRoomDB
import com.kgb.kapp.db.entity.TemplateEntity
import com.kgb.kapp.db.entity.UserProgressEntity
import java.util.*
import java.util.concurrent.Executors
import kotlin.collections.ArrayList


class ChallengesRepository private constructor(context: Context) {
    val db = ChallengeRoomDB.getInstance(context)
    val executor = Executors.newSingleThreadExecutor()

    fun getDayChallenges(date: Date): LiveData<List<ChallengeEntity>> {
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

    fun deleteAll() {
        executor.execute {
            db.noteDao().deleteAll()
        }
    }

    fun update(challenge: ChallengeEntity) {
        executor.execute {
            db.noteDao().insertChallenge(challenge)
            db.userDao().update(UserProgressEntity(
                challenge.challengeName,
                challenge.step,
                challenge.progress,
                challenge.goal,
                challenge.series
            ))
        }
    }

    fun deleteChallenge(challenge: ChallengeEntity) {
        executor.execute {
            db.noteDao().deleteChallenge(challenge)
        }
    }

    fun getChallengeById(challengeId: Long): ChallengeEntity {
        return db.noteDao().getChallengeById(challengeId)
    }

    fun getTemplates(): LiveData<List<TemplateEntity>> {
        return db.templateDao().getAll()
    }

    fun loadDataFromTemplate(templateEntity: TemplateEntity, date: Date) {
        executor.execute {
            val challengesType = db.templateDao().loadTemplateType(templateEntity.id!!)
            val challengesList = ArrayList<ChallengeEntity>()
            for (type in challengesType) {
                val challengeProgress = db.userDao().getChallengeProgress(type.challengeType)
                    ?: createUserProgress(type.challengeType)
                challengesList.add(ChallengeEntity(challengeProgress, date))
            }
            db.noteDao().insertAll(challengesList)
        }
    }

    private fun createUserProgress(challengeType: ChallengeType): UserProgressEntity {
        val result = UserProgressEntity.createNew(challengeType)
        db.userDao().update(result)
        return result
    }

    companion object {
        @Volatile
        var instance: ChallengesRepository? = null
        val lock = Any()

        fun getInstance(context: Context): ChallengesRepository {
            if (instance == null) {
                synchronized(lock) {
                    if (instance == null) {
                        instance = ChallengesRepository(context)
                    }
                }
            }
            return instance!!
        }
    }

}