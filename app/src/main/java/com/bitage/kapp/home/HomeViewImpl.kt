package com.bitage.kapp.home

import android.content.Intent
import android.databinding.DataBindingUtil
import android.view.View
import android.widget.CalendarView
import com.bitage.kapp.presentation.Constants
import com.bitage.kapp.R
import com.bitage.kapp.daychallenges.TodayChallengesActivity
import com.bitage.kapp.databinding.ChallengesMainBinding
import com.bitage.kapp.ui.view.RoundCalendarView
import java.util.Calendar

/**
 * Implementation of home screen
 */
class HomeViewImpl(private val activity: HomeActivity) : HomeView {

    private lateinit var binding: ChallengesMainBinding

    /**
     * Controls lifecycle of this view. It should be called in presenter onCreate method
     */
    override fun onCreate() {
        binding = DataBindingUtil.setContentView(activity, R.layout.challenges_main)
        initCalendar()
    }

    /**
     * Controls lifecycle of this view. It should be called in presenter onDestroy method
     */
    override fun onDestroy() {
        binding.unbind()
    }

    /**
     * get the real android view
     */
    override fun androidView(): View = binding.root

    private fun initCalendar() {
        binding.roundCalendar.setOnDateClickListener ( object : RoundCalendarView.OnDateClickListener {
            override fun onClick(view: RoundCalendarView, year: Int, month: Int, dayOfMonth: Int) {
                val intent = Intent(activity, TodayChallengesActivity::class.java)
                intent.putExtra(Constants.CURRENT_DATE_DAY, dayOfMonth)
                intent.putExtra(Constants.CURRENT_DATE_MONTH, month)
                intent.putExtra(Constants.CURRENT_DATE_YEAR, year)
                activity.startActivity(intent)
            }
        })
    }
}