package com.bitage.kapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bitage.kapp.entity.UserProgressEntity
import com.bitage.kapp.model.ChallengeType

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