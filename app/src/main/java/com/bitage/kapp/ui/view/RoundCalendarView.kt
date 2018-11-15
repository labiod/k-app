package com.bitage.kapp.ui.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.text.format.DateUtils
import android.util.AttributeSet
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import com.bitage.kapp.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RoundCalendarView(context: Context, attributeSet: AttributeSet?, defStyle: Int)
    : ConstraintLayout(context, attributeSet, defStyle) {
    interface OnDateClickListener {
        fun onClick(view: RoundCalendarView, year: Int, month: Int, dayOfMonth: Int)
    }
    private val calendarView: CalendarView
    private val selectedDay: TextView
    private val leftButton: Button
    private val rightButton: Button
    private var listener: OnDateClickListener? = null

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attributeSet: AttributeSet) : this(context, attributeSet, 0)

    init {
        inflate(context, R.layout.round_calendar_layout, this)
        calendarView = findViewById(R.id.challengesCalendar)
        selectedDay = findViewById(R.id.selectedDay)
        leftButton = findViewById(R.id.leftButton)
        rightButton = findViewById(R.id.rightButton)
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd MMM, ''yy", Locale.getDefault())
        calendar.timeInMillis = calendarView.date
        selectedDay.text = dateFormat.format(calendar.time)
        leftButton.setOnClickListener {
            calendar.timeInMillis = calendarView.date
            calendar.add(Calendar.DAY_OF_MONTH, -1)
            calendarView.setDate(calendar.timeInMillis, true, false)
            selectedDay.text = dateFormat.format(calendar.time)

        }
        rightButton.setOnClickListener {
            calendar.timeInMillis = calendarView.date
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            calendarView.date = calendar.timeInMillis
            calendarView.setDate(calendar.timeInMillis, true, false)
            selectedDay.text = dateFormat.format(calendar.time)
        }
        selectedDay.setOnClickListener {
            listener?.let { l ->
                calendar.timeInMillis = calendarView.date
                l.onClick(
                    this@RoundCalendarView,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
            }
        }
    }

    fun setOnDateClickListener(listener: OnDateClickListener) {
        this.listener = listener
    }
}