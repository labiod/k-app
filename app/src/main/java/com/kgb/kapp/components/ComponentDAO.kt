package com.kgb.kapp.components

class ComponentDAO(val challengeType: ChallengeType, val step: Int, val stepProgress: StepProgress) {
    companion object {
        const val MAX_LEVEL = 10
        fun getGoalForChallenges(challengeType: ChallengeType, step: Int, stepProgress: StepProgress): Int {
            return 40
        }

        fun getSeriesForStepProgress(stepProgress: StepProgress): Int {
            return stepProgress.steps
        }
    }

    var finished: Boolean = false

    val goal: Int = getGoalForChallenges(challengeType, step, stepProgress)
    val series: Int = getSeriesForStepProgress(stepProgress)
}