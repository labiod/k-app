package com.kgb.kapp.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.kgb.kapp.challenge.ChallengeType
import com.kgb.kapp.challenge.StepProgress
import java.util.*

@Entity(tableName = "challenges")
data class ChallengeEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
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
    constructor(challengeName : ChallengeType, step : Int, progress : StepProgress)
        : this(null, challengeName, step, progress, Date())
}