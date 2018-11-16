package com.bitage.kapp.ui.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import com.bitage.kapp.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * Android view with calendar to display current date in circle
 */
class RoundCalendarView(context: Context, attributeSet: AttributeSet?, defStyle: Int)
    : LinearLayout(context, attributeSet, defStyle) {
    /**
     * OnDateClickListener call when user clicked on calendar day
     */
    interface OnDateListener {
        /**
         * Call when user clicked on calendar
         * @param view - calendar view
         * @param year - year for clicked item
         * @param month - month for clicked item
         * @param dayOfMonth - day for clicked item
         */
        fun onClick(view: RoundCalendarView, year: Int, month: Int, dayOfMonth: Int)

        /**
         * Call whe user choose day from calendar, or use left/rigth button to skip to previous/next day
         * @param view - calendar view
         * @param year - year for clicked item
         * @param month - month for clicked item
         * @param dayOfMonth - day for clicked item
         */
        fun onDateChange(view: RoundCalendarView, year: Int, month: Int, dayOfMonth: Int)
    }
    private val calendarView: CalendarView
    private val selectedDay: TextView
    private val progressView: TextView
    private val leftButton: ImageButton
    private val rightButton: ImageButton
    private val expandCalendarButton: ImageButton
    private val selectedDayLayout: ViewGroup
    private var listener: OnDateListener? = null
    private var actual = 0
    private var max = 0

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attributeSet: AttributeSet) : this(context, attributeSet, 0)

    init {
        inflate(context, R.layout.round_calendar_layout, this)
        calendarView = findViewById(R.id.challengesCalendar)
        selectedDay = findViewById(R.id.selectedDay)
        progressView = findViewById(R.id.progressView)
        selectedDayLayout = findViewById(R.id.selectedDayLayout)
        leftButton = findViewById(R.id.leftButton)
        rightButton = findViewById(R.id.rightButton)
        expandCalendarButton = findViewById(R.id.expandCalendar)
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd MMM, ''yy", Locale.getDefault())
        calendar.timeInMillis = calendarView.date
        selectedDay.text = dateFormat.format(calendar.time)
        calendarView.visibility = View.GONE
        calendarView.setBackgroundColor(Color.TRANSPARENT)
        calendarView.setOnDateChangeListener {
            view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            calendarView.setDate(calendar.timeInMillis, true, false)
            selectedDay.text = dateFormat.format(calendar.time)
            expandCalendar(false)
            listener?.onDateChange(this@RoundCalendarView, year, month, dayOfMonth)
        }
        leftButton.setOnClickListener {
            calendar.timeInMillis = calendarView.date
            calendar.add(Calendar.DAY_OF_MONTH, -1)
            calendarView.setDate(calendar.timeInMillis, true, false)
            selectedDay.text = dateFormat.format(calendar.time)
            listener?.onDateChange(this@RoundCalendarView,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))
        }
        rightButton.setOnClickListener {
            calendar.timeInMillis = calendarView.date
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            calendarView.date = calendar.timeInMillis
            calendarView.setDate(calendar.timeInMillis, true, false)
            selectedDay.text = dateFormat.format(calendar.time)
            listener?.onDateChange(this@RoundCalendarView,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))
        }
        selectedDayLayout.setOnClickListener {
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
        expandCalendarButton.setOnClickListener {
            expandCalendar(calendarView.visibility == View.GONE)
        }
    }

    /**
     * Set listener for calendar view which will be call when user click on any date on calendar
     * @param listener - listener object
     */
    fun setOnDateListener(listener: OnDateListener) {
        this.listener = listener
    }

    /**
     * Set progress for current date
     * @param actualProgress - actual progress for date
     * @param max - max value for date
     */
    fun setProgress(actualProgress: Int, max: Int) {
        this.actual = actualProgress
        this.max = max
        progressView.text = resources.getString(R.string.show_progress_templ_text, actual, max)
    }

    /**
     * Get selected date for calendar view
     * @return selected date
     */
    fun getSelectedDate(): Date = Date(calendarView.date)

    private fun expandCalendar(expanded: Boolean) {
        if (expanded) {
            calendarView.visibility = View.VISIBLE
            calendarView.animate().alpha(1f)
        } else {
            calendarView.visibility = View.GONE
            calendarView.animate().alpha(0f)
        }
    }
}