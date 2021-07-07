package com.bitage.kapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bitage.kapp.model.ChallengeType
import com.bitage.kapp.model.StepProgress

/**
 * Entity for use progress that will be retrieve fro database
 * @property id - primary key
 * @property challengeType - challenge type see [ChallengeType]
 */
@Entity(tableName = "user_progress")
data class UserProgressEntity(
    /**
     * Challenge type see [ChallengeType]
     */
    @PrimaryKey(autoGenerate = false)
    val challengeType: ChallengeType,
    /**
     * challenge step value
     */
    val step: Int,
    /**
     * Challenge step progress see [StepProgress]
     */
    val stepProgress: StepProgress,
    /**
     * Challenge goal
     */
    val goal: Int,
    /**
     * Challenge number of series
     */
    val series: Int
) {
    companion object {
        /**
         * Create new progress for given challenge with lower settings
         * @param type - challenge typ
         * @return challenge progress
         */
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