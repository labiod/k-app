package com.kgb.kapp.challenge

import com.kgb.kapp.R

enum class StepProgress(val levelTextRes: Int) {
    BEGINNER(R.string.level_beginner),
    ADVANCE(R.string.level_intermediate),
    PROGRESSION(R.string.level_progression),
    CUSTOM(R.string.level_custom)
}