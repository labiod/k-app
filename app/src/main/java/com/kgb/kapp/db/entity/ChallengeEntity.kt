package com.kgb.kapp.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.kgb.kapp.challenge.ChallengeType
import com.kgb.kapp.challenge.StepProgress
import java.util.*

@Entity(tableName = "challenges")
data class ChallengeEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long? = null,
    val challengeName : ChallengeType,
    val step : Int,
    val progress : StepProgress,
    val date : Date,
    val series : Int = 0,
    val goal : Int = 0,
    val finished: Boolean = false
    ) {
    fun setFinishChallenge(finishChallenge: Boolean): ChallengeEntity {
        return ChallengeEntity(id, challengeName, step, progress, date, series, goal, finishChallenge)
    }

    @Ignore
    constructor(challengeName : ChallengeType, step : Int, progress : StepProgress, date: Date)
        : this(null, challengeName, step, progress, date)

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