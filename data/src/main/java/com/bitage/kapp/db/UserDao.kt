package com.bitage.kapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bitage.kapp.entity.UserInfoEntity
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
    suspend fun getChallengeProgress(challengeType: ChallengeType): UserProgressEntity?

    /**
     * Insert challenge progress to database
     * @param challengeProgress - challenge progress object
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(challengeProgress: UserProgressEntity)

    /**
     * Insert user info to databse
     * @param userInfo - user info fields
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(userInfo: List<UserInfoEntity>)

    /**
     * Get user info
     */
    @Query("SELECT * FROM user_info")
    suspend fun getUserInfo(): List<UserInfoEntity>
}