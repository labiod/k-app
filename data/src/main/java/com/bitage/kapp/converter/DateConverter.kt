package com.bitage.kapp.converter

import androidx.room.TypeConverter
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
    fun toDate(timestamp: Long?): Date? = timestamp?.let { Date(timestamp) }

    /**
     * Convert [Date] to timestamp
     * @param time - date to convert
     * @return timestamp for give date
     */
    @TypeConverter
    fun toTimestamp(time: Date?): Long? = time?.let { time.time }
}