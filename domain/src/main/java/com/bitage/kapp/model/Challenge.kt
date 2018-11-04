package com.bitage.kapp.model

import java.util.Date

class Challenge(
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
     * Constructor class used to create challenge for given data. Not used by database
     * @param challengeName - challenge type
     * @param step - challenge step
     * @param progress - challenge step progress
     * @param date - challenge date
     */
    constructor(challengeName: ChallengeType, step: Int, progress: StepProgress, date: Date)
        : this(null, challengeName, step, progress, date)

    /**
     * Constructor class used to create challenge from [Challenge] instance and date
     * Not used by database
     * @param challenge - given challenge
     * @param date - given date
     */
    constructor(challenge: Challenge, date: Date)
        : this(
        null,
        challenge.challengeName,
        challenge.step,
        challenge.progress,
        date,
        challenge.series,
        challenge.goal
    )
    /**
     * Set finish flag for challenge and return new instance of ChallengeEntity
     * @param finishChallenge - finish flag
     * @return new challenge instance
     */
    fun setFinishChallenge(finishChallenge: Boolean): Challenge {
        return Challenge(id, challengeName, step, progress, date, series, goal, finishChallenge)
    }


    /**
     * Apply changes in challenge, because object is immutable this method return new object base on current object
     * @param newStep - challenge step value
     * @param newProgress - challenge progress value
     * @param newGoal - challenge goal value
     * @param newSeries - challenge series value
     * @return new challenge based on current challenge
     */
    fun applyChanges(newStep: Int, newProgress: StepProgress, newGoal: Int, newSeries: Int): Challenge {
        return Challenge(
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