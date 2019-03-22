package com.bitage.kapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bitage.kapp.entity.ChallengeEntity

/**
 * Dao class that retrieve and manipulate (update/insert/delete) challenges data from database
 */
@Dao
interface ChallengeDao {
    /**
     * Insert challenge to database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChallenge(challenge: ChallengeEntity): Long

    /**
     * Insert list of challenges to database
     * @param notes - list of challenges
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(notes: List<ChallengeEntity>)

    /**
     * Delete challenge from database
     * @param challengeEntity - challenge that will be delete
     */
    @Delete
    suspend fun deleteChallenge(challengeEntity: ChallengeEntity)

    /**
     * Delete all challenges from database
     */
    @Query("DELETE FROM challenges")
    suspend fun deleteAll()

    /**
     * Query all challenges between given dates
     * @param dayStart - start date for retrieved challenges
     * @param dayEnd - end date for retrieved challenges
     * @return list of challenges
     */
    @Query("SELECT * FROM challenges WHERE date BETWEEN :dayStart AND :dayEnd")
    suspend fun getChallengeAtDate(dayStart: Long, dayEnd: Long): List<ChallengeEntity>

    /**
     * Query all challenges from database
     * @return list of challenges
     */
    @Query("SELECT * FROM challenges")
    suspend fun getAll(): List<ChallengeEntity>

    /**
     * Query challenge for given id
     * @param challengeId - challenge id
     * @return challenge for given id
     */
    @Query("SELECT * FROM challenges WHERE id = :challengeId")
    suspend fun getChallengeById(challengeId: Long): ChallengeEntity
}