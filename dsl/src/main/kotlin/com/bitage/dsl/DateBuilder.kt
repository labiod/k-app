package com.bitage.dsl

import java.util.Calendar
import java.util.Date

class DateBuilder {
    var day: Int = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var seconds: Int = 0

    fun build(): Date {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, hour, minute, seconds)
        return calendar.time
    }
}