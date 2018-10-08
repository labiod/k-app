package com.kgb.kapp.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.kgb.kapp.challenge.ChallengeType
import com.kgb.kapp.challenge.StepProgress

@Entity(tableName = "user_progress")
data class UserProgressEntity(
    @PrimaryKey(autoGenerate = false)
    val challengeType: ChallengeType,
    val step: Int,
    val stepProgress: StepProgress,
    val goal: Int,
    val series: Int
) {
    companion object {
        fun createNew(type: ChallengeType): UserProgressEntity {
            return UserProgressEntity(
                type,
                1,
                StepProgress.BEGINNER,
                1,
                1
            )
        }
    }
}