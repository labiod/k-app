package com.kgb.kapp.helper

import com.kgb.kapp.R

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