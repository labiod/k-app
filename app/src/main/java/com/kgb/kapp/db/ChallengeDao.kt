package com.kgb.kapp.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface ChallengeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChallenge(challengeEntity: ChallengeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(notes: List<ChallengeEntity>)

    @Delete
    fun deleteChallenge(challengeEntity: ChallengeEntity)

    @Query("DELETE FROM challenges")
    fun deleteAll()

    @Query("SELECT * FROM challenges WHERE date BETWEEN :dayStart AND :dayEnd")
    fun getChallengeAtDate(dayStart: Long, dayEnd: Long) : LiveData<List<ChallengeEntity>>

    @Query("SELECT * FROM challenges")
    fun getAll() : LiveData<List<ChallengeEntity>>

    @Query("SELECT * FROM challenges WHERE id = :challengeId")
    fun getChallengeById(challengeId : Int) : ChallengeEntity

}