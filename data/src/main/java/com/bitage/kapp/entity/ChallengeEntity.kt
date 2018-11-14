package com.bitage.kapp.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.bitage.kapp.model.ChallengeType
import com.bitage.kapp.model.StepProgress
import java.util.Date

/**
 * Entity class for challenge that will be retrieve from database
 * @property id - primary key
 */
@Entity(tableName = "challenges")
data class ChallengeEntity(
    /**
     * primary key for challenges table
     */
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    /**
     * challenge name
     */
    val challengeName: ChallengeType,
    /**
     * challenge step
     */
    val step: Int,
    /**
     * step progress
     */
    val progress: StepProgress,
    /**
     * challenge date
     */
    val date: Date,
    /**
     * number of series
     */
    val series: Int = 0,
    /**
     * challenge goal
     */
    val goal: Int = 0,
    /**
     * challenge finish flag
     */
    val finished: Boolean = false
) {
    /**
     * Constructor class used to create challenge from [UserProgressEntity] instance and date
     * Not used by database
     * @param challengeProgress - given challenge progress
     * @param date - given date
     */
    @Ignore
    constructor(challengeProgress: UserProgressEntity, date: Date)
        : this(
            null,
            challengeProgress.challengeType,
            challengeProgress.step,
            challengeProgress.stepProgress,
            date,
            challengeProgress.goal,
            challengeProgress.goal
        )
}