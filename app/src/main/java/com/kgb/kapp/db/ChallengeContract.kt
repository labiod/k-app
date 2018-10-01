package com.kgb.kapp.db

import android.provider.BaseColumns

val DATABASE_NAME = "habittrainer.db"
val DATABASE_VERSION = 1

object ChallengeEntry : BaseColumns {
    val TABLE_NAME = "challenge"
    val _ID = "id"

    val TYPE_COL = "challenge_type"
    val STEP_COL = "challenge_step"
    val STEP_PROGRESS_COL = "challenge_step_progress"
}

object ChallengeStepEntry : BaseColumns {
    val _ID = "id"
    val TABLE_NAME = "challenge_name"
    val TYPE_COL = "type"
    val DESCR_COL = "description"
}