package com.kgb.kapp.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.kgb.kapp.challenge.ChallengeType
import com.kgb.kapp.db.entity.UserProgressEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user_progress WHERE challengeType = :challengeType")
    fun getChallengeProgress(challengeType: ChallengeType): UserProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(challengeProgress: UserProgressEntity)
}