package com.bitage.naw_views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.CalendarView
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.Date
import android.view.ViewGroup
import com.bitage.dsl.createDate
import com.bitage.dsl.get
import java.util.Calendar


class CalendarButton(context: Context, attributeSet: AttributeSet?, defStyle: Int)
    : ConstraintLayout(context, attributeSet, defStyle) {
    /**
     * OnDateClickListener call when user clicked on calendar day
     */
    interface OnDateListener {
        /**
         * Call whe user choose day from calendar, or use left/rigth button to skip to previous/next day
         * @param view - calendar view
         * @param year - year for clicked item
         * @param month - month for clicked item
         * @param dayOfMonth - day for clicked item
         */
        fun onDateChange(view: CalendarButton, year: Int, month: Int, dayOfMonth: Int)
    }
    private val calendarView: CalendarView
    private val expandCalendarButton: ImageButton
    private var listener: OnDateListener? = null

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attributeSet: AttributeSet) : this(context, attributeSet, 0)

    init {
        inflate(context, R.layout.calendar_button_layout, this)
        calendarView = LayoutInflater.from(context)
            .inflate(R.layout.calendar_layout, this.parent as ViewGroup?, false) as CalendarView
        expandCalendarButton = findViewById(R.id.expandCalendar)
        disableClipOnParents(calendarView)
        calendarView.visibility = View.GONE
        calendarView.setBackgroundColor(Color.TRANSPARENT)
        calendarView.setOnDateChangeListener {
            _, year, month, dayOfMonth ->
            val date = createDate {
                day = dayOfMonth
                this.month = month
                this.year = year
            }
            calendarView.setDate(date.time, true, false)
            expandCalendar(false)
            listener?.onDateChange(this@CalendarButton, year, month, dayOfMonth)
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
     * Get selected date for calendar view
     * @return selected date
     */
    fun getSelectedDate(): Date = Date(calendarView.date)

    private fun expandCalendar(expanded: Boolean) {
        if (calendarView.parent == null) {
            (this.parent as ViewGroup).addView(calendarView)
        }
        if (expanded) {
            calendarView.visibility = View.VISIBLE
            calendarView.animate().alpha(1f)
        } else {
            calendarView.visibility = View.GONE
            calendarView.animate().alpha(0f)
        }
    }

    fun setDate(timeInMillis: Long, animate: Boolean, center: Boolean = false) {
        calendarView.date = timeInMillis
        calendarView.setDate(timeInMillis, animate, center)
        listener?.onDateChange(this,
            timeInMillis get Calendar.YEAR,
            timeInMillis get Calendar.MONTH,
            timeInMillis get Calendar.DAY_OF_MONTH
        )
    }

    private fun disableClipOnParents(v: View?) {
        if (v == null) {
            return
        }
        if (v is ViewGroup) {
            v.clipChildren = false
        }
        disableClipOnParents(v.parent as View?)
    }
}
