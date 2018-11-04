package com.bitage.kapp.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.bitage.kapp.entity.ChallengeEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

/**
 * Dao class that retrieve and manipulate (update/insert/delete) challenges data from database
 */
@Dao
interface ChallengeDao {
    /**
     * Insert challenge to database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChallenge(challengeEntity: ChallengeEntity): Long

    /**
     * Insert list of challenges to database
     * @param notes - list of challenges
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(notes: List<ChallengeEntity>)

    /**
     * Delete challenge from database
     * @param challengeEntity - challenge that will be delete
     */
    @Delete
    fun deleteChallenge(challengeEntity: ChallengeEntity)

    /**
     * Delete all challenges from database
     */
    @Query("DELETE FROM challenges")
    fun deleteAll()

    /**
     * Query all challenges between given dates
     * @param dayStart - start date for retrieved challenges
     * @param dayEnd - end date for retrieved challenges
     * @return list of challenges
     */
    @Query("SELECT * FROM challenges WHERE date BETWEEN :dayStart AND :dayEnd")
    fun getChallengeAtDate(dayStart: Long, dayEnd: Long): Flowable<List<ChallengeEntity>>

    /**
     * Query all challenges from database
     * @return list of challenges
     */
    @Query("SELECT * FROM challenges")
    fun getAll(): Flowable<List<ChallengeEntity>>

    /**
     * Query challenge for given id
     * @param challengeId - challenge id
     * @return challenge for given id
     */
    @Query("SELECT * FROM challenges WHERE id = :challengeId")
    fun getChallengeById(challengeId: Long): ChallengeEntity
}