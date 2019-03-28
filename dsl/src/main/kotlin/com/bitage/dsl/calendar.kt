package com.bitage.dsl

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@DslMarker
annotation class DateDsl

fun createDate(creator: DateBuilder.() -> Unit) = DateBuilder().apply(creator).build()

@DateDsl
infix fun Long.dayDiff(diff: Int): Long {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    calendar.add(Calendar.DAY_OF_MONTH, diff)
    return calendar.timeInMillis
}

infix fun Date.get(type: Int): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(type)
}

infix fun Long.get(type: Int): Int {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return calendar.get(type)
}

infix fun Date.format(format: String): String {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    return dateFormat.format(this)
}
