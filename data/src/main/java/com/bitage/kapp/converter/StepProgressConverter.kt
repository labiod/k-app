package com.bitage.kapp.converter

import android.arch.persistence.room.TypeConverter
import com.bitage.kapp.model.StepProgress

/**
 * Converter class using by database for [StepProgress] enum
 */
class StepProgressConverter {
    /**
     * Convert [String] to [StepProgress]
     * @param name - item to convert
     * @return [StepProgress]
     */
    @TypeConverter
    fun toStepProgress(name: String): StepProgress {
        return StepProgress.valueOf(name)
    }

    /**
     * Convert [StepProgress] to [String]
     * @param stepProgress - item to convert
     * @return [String]
     */
    @TypeConverter
    fun toStepProgressName(stepProgress: StepProgress): String {
        return stepProgress.name
    }
}