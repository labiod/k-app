package com.kgb.kapp.db.converter

import android.arch.persistence.room.TypeConverter
import com.kgb.kapp.challenge.StepProgress

class StepProgressConverter {
    @TypeConverter
    fun toStepProgress(name: String): StepProgress {
        return StepProgress.valueOf(name)
    }

    @TypeConverter
    fun toStepProgressName(stepProgress: StepProgress): String {
        return stepProgress.name
    }
}