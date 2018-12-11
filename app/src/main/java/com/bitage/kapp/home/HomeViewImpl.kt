package com.bitage.kapp.home

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.view.View
import com.bitage.kapp.presentation.Constants
import com.bitage.kapp.R
import com.bitage.kapp.daychallenges.TodayChallengesActivity
import com.bitage.kapp.databinding.ChallengesMainBinding
import com.bitage.kapp.ui.view.RoundCalendarView
import io.reactivex.functions.Consumer

/**
 * Implementation of home screen
 */
class HomeViewImpl(private val activity: HomeActivity) : HomeView {

    private val binding: ChallengesMainBinding by lazy {
        DataBindingUtil.setContentView<ChallengesMainBinding>(activity, R.layout.challenges_main)
    }
    private lateinit var viewModel: HomeViewModel

    /**
     * Controls lifecycle of this view. It should be called in presenter onCreate method
     */
    override fun onCreate() {
        initCalendar()
    }

    /**
     * Controls lifecycle of this view. It should be called in presenter onDestroy method
     */
    override fun onDestroy() {
        binding.unbind()
    }

    override fun attachViewModel(viewModel: HomeViewModel) {
        this.viewModel = viewModel
        binding.viewmodel = viewModel
        viewModel.dateData.observe(activity, Observer {
            viewModel.getDayChallengesState(Consumer { p ->
                binding.roundCalendar.setProgress(p.first, p.second)
            })
        })
        viewModel.dateData.postValue(binding.roundCalendar.getSelectedDate())
    }

    /**
     * get the real android view
     */
    override fun androidView(): View = binding.root

    private fun initCalendar() {
        binding.roundCalendar.setOnDateListener(object : RoundCalendarView.OnDateListener {
            override fun onClick(view: RoundCalendarView, year: Int, month: Int, dayOfMonth: Int) {
                val intent = Intent(activity, TodayChallengesActivity::class.java)
                intent.putExtra(Constants.CURRENT_DATE_DAY, dayOfMonth)
                intent.putExtra(Constants.CURRENT_DATE_MONTH, month)
                intent.putExtra(Constants.CURRENT_DATE_YEAR, year)
                activity.startActivity(intent)
            }

            override fun onDateChange(view: RoundCalendarView, year: Int, month: Int, dayOfMonth: Int) {
                viewModel.dateData.postValue(binding.roundCalendar.getSelectedDate())
            }
        })
    }
}