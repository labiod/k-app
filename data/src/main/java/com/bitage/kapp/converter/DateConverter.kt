package com.bitage.kapp.converter

import android.arch.persistence.room.TypeConverter
import java.util.Date

/**
 * Converter class using by database for [Date] object
 */
class DateConverter {
    /**
     * Convert timestamp to [Date]
     * @param timestamp - time to convert
     * @return date for given timestamp
     */
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(timestamp) }
    }

    /**
     * Convert [Date] to timestamp
     * @param time - date to convert
     * @return timestamp for give date
     */
    @TypeConverter
    fun toTimestamp(time: Date?): Long? {
        return time?.let { time.time }
    }
}