package com.kgb.kapp.challenge

import com.kgb.kapp.R

/**
 * Enum class that contains all progress for challenge step
 */
enum class StepProgress(
    /**
     * res id value to display proper name for support lang
     */
    val levelTextRes: Int
) {
    /**
     * beginner step
     */
    BEGINNER(R.string.level_beginner),
    /**
     * advance step
     */
    ADVANCE(R.string.level_intermediate),
    /**
     * progression step
     */
    PROGRESSION(R.string.level_progression),
    /**
     * custom step
     */
    CUSTOM(R.string.level_custom)
}