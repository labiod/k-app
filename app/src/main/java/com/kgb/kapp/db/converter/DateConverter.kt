package com.kgb.kapp.db.converter

import android.arch.persistence.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(timestamp) }
    }

    @TypeConverter
    fun toTimestamp(time: Date?): Long? {
        return time?.let { time.time }
    }
}