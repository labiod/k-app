package com.kgb.kapp.db

import android.arch.lifecycle.LiveData
import android.content.Context
import com.kgb.kapp.challenge.ChallengeType
import com.kgb.kapp.challenge.StepProgress
import java.util.*
import java.util.concurrent.Executors


class ChallengesRepository private constructor(context: Context) {
    val db = ChallengeRoomDB.getInstance(context)
    val challenges : LiveData<List<ChallengeEntity>> = getAllChallenges()
    val executor = Executors.newSingleThreadExecutor()

    private fun getAllChallenges(): LiveData<List<ChallengeEntity>> {
        val cal = Calendar.getInstance()
        cal.time = Date()
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

    fun addSampleData() {
        val list = ArrayList<ChallengeEntity>()
        list.add(ChallengeEntity(ChallengeType.PUSHUP, 1, StepProgress.BEGINNER))
        list.add(ChallengeEntity(ChallengeType.PULLUP, 1, StepProgress.BEGINNER))
        list.add(ChallengeEntity(ChallengeType.SQUAT, 1, StepProgress.BEGINNER))
        list.add(ChallengeEntity(ChallengeType.BRIDGE, 1, StepProgress.BEGINNER))
        list.add(ChallengeEntity(ChallengeType.LEG_RAISE, 1, StepProgress.BEGINNER))
        list.add(ChallengeEntity(ChallengeType.HANDSTAND_PUSHUP, 1, StepProgress.BEGINNER))
        executor.execute {
            db.noteDao().insertAll(list)
        }

    }

    fun deleteAll() {
        executor.execute {
            db.noteDao().deleteAll()
        }
    }

    fun update(challenge: ChallengeEntity) {
        executor.execute {
            db.noteDao().insertChallenge(challenge)
        }
    }

    fun deleteChallenge(challenge: ChallengeEntity) {
        executor.execute {
            db.noteDao().deleteChallenge(challenge)
        }
    }

    fun getChallengeById(challengeId: Int): ChallengeEntity {
        return db.noteDao().getChallengeById(challengeId)
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