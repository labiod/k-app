package com.kgb.kapp.components

import com.kgb.kapp.R

enum class StepProgress(val levelTextRes: Int, val steps: Int) {
    BEGINNER(R.string.level_beginner, 1),
    ADVANCE(R.string.level_intermediate, 2),
    PROGRESSION(R.string.level_progression, 3)
}