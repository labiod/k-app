package com.kgb.kapp.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.kgb.kapp.challenge.ChallengeType
import com.kgb.kapp.db.entity.UserProgressEntity

/**
 * Dao class that retrieve and manipulate (update/insert/delete) user data from database
 */
@Dao
interface UserDao {
    /**
     * Query challenge progress for user
     * @param challengeType - challenge type
     * @return progress for given challenge
     */
    @Query("SELECT * FROM user_progress WHERE challengeType = :challengeType")
    fun getChallengeProgress(challengeType: ChallengeType): UserProgressEntity?

    /**
     * Insert challenge progress to database
     * @param challengeProgress - challenge progress object
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(challengeProgress: UserProgressEntity)
}