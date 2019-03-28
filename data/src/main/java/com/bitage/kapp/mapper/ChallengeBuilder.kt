package com.bitage.kapp.mapper

import com.bitage.kapp.entity.ChallengeEntity
import com.bitage.kapp.model.Challenge
import com.bitage.kapp.model.ChallengeType
import com.bitage.kapp.model.StepProgress
import java.util.Date

class ChallengeBuilder {
    var id: Long? = null
    var step: Int = 0
    lateinit var stepProgress: StepProgress
    lateinit var challengeName: ChallengeType
    lateinit var date: Date
    var series: Int = 0
    var goal: Int = 0
    var finished: Boolean = false

    fun challenge(): Challenge {
        return Challenge(
            id,
            challengeName,
            step,
            stepProgress,
            date,
            series,
            goal,
            finished
        )
    }

    fun challengeEntity(): ChallengeEntity {
        return ChallengeEntity(
            id,
            challengeName,
            step,
            stepProgress,
            date,
            series,
            goal,
            finished
        )
    }
}