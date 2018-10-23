package com.kgb.kapp.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.kgb.kapp.challenge.ChallengeType
import com.kgb.kapp.challenge.StepProgress
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
     * Set finish flag for challenge and return new instance of ChallengeEntity
     * @param finishChallenge - finish flag
     * @return new challenge instance
     */
    fun setFinishChallenge(finishChallenge: Boolean): ChallengeEntity {
        return ChallengeEntity(id, challengeName, step, progress, date, series, goal, finishChallenge)
    }

    /**
     * Constructor class used to create challenge for given data. Not used by database
     * @param challengeName - challenge type
     * @param step - challenge step
     * @param progress - challenge step progress
     * @param date - challenge date
     */
    @Ignore
    constructor(challengeName: ChallengeType, step: Int, progress: StepProgress, date: Date)
        : this(null, challengeName, step, progress, date)

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

    /**
     * Apply changes in challenge, because object is immutable this method return new object base on current object
     * @param newStep - challenge step value
     * @param newProgress - challenge progress value
     * @param newGoal - challenge goal value
     * @param newSeries - challenge series value
     * @return new challenge based on current challenge
     */
    fun applyChanges(newStep: Int, newProgress: StepProgress, newGoal: Int, newSeries: Int): ChallengeEntity {
        return ChallengeEntity(
            id,
            challengeName,
            newStep,
            newProgress,
            date,
            newSeries,
            newGoal,
            finished
        )
    }
}