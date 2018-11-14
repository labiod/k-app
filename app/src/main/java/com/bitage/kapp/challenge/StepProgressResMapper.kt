package com.bitage.kapp.challenge

import com.bitage.kapp.R

/**
 * Enum used to map StepProgress enum to android resources
 */
enum class StepProgressResMapper(val resId: Int) {
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