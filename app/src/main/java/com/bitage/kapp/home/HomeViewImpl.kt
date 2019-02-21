package com.bitage.kapp.home

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.bitage.kapp.R
import com.bitage.kapp.Screen
import com.bitage.kapp.databinding.ChallengesMainBinding
import com.bitage.kapp.daychallenges.TodayChallengesActivity
import com.bitage.kapp.presentation.Constants
import com.bitage.naw_views.CalendarButton
import io.reactivex.functions.Consumer
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * Implementation of home screen
 */
class HomeViewImpl : HomeView {

    private val binding: ChallengesMainBinding by lazy {
        DataBindingUtil.setContentView<ChallengesMainBinding>(screen.getActivity(), R.layout.challenges_main)
    }
    private lateinit var viewModel: HomeViewModel
    private lateinit var screen: Screen

    /**
     * Controls lifecycle of this view. It should be called in presenter onCreate method
     */
    override fun onCreate() {
    }

    override fun onAttached(screen: Screen) {
        this.screen = screen
        initView()
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
        with(binding) {
            val dateFormat = SimpleDateFormat("dd MMM, ''yy", Locale.getDefault())
            viewModel.dateData.observe(screen, Observer {
                selectedDay.text = dateFormat.format(it)
                viewModel.getDayChallengesState(Consumer { p ->
                    roundProgress.setProgress(p.first, p.second)
                })
            })
            val calendar = Calendar.getInstance()

            leftButton.setOnClickListener {
                calendar.timeInMillis = calendarButton.getSelectedDate().time
                calendar.add(Calendar.DAY_OF_MONTH, -1)
                calendarButton.setDate(calendar.timeInMillis, true, false)

            }
            rightButton.setOnClickListener {
                calendar.timeInMillis = calendarButton.getSelectedDate().time
                calendar.add(Calendar.DAY_OF_MONTH, 1)
                calendarButton.setDate(calendar.timeInMillis, true, false)
            }
            viewModel.dateData.postValue(calendarButton.getSelectedDate())
        }
    }

    /**
     * get the real android view
     */
    override fun androidView(): View = binding.root

    override fun customizeActionBar(actionBar: ActionBar?) {}

    private fun initView() {
        binding.calendarButton.setOnDateListener(object : CalendarButton.OnDateListener {
            override fun onDateChange(view: CalendarButton, year: Int, month: Int, dayOfMonth: Int) {
                viewModel.dateData.postValue(view.getSelectedDate())
            }
        })

        binding.roundProgress.setOnClickListener {
            val date = binding.calendarButton.getSelectedDate()
            if (isDateExpire(date)) {
                Toast.makeText(binding.root.context, "You can not change challenges this day", Toast.LENGTH_SHORT).show()
            } else {
                val calendar = Calendar.getInstance()
                calendar.time = date
                val intent = Intent(screen.getActivity(), TodayChallengesActivity::class.java)
                intent.putExtra(Constants.CURRENT_DATE_DAY, calendar.get(Calendar.DAY_OF_MONTH))
                intent.putExtra(Constants.CURRENT_DATE_MONTH, calendar.get(Calendar.MONTH))
                intent.putExtra(Constants.CURRENT_DATE_YEAR, calendar.get(Calendar.YEAR))
                screen.startActivity(intent)
            }
        }
    }

    private fun isDateExpire(date: Date) : Boolean {
        val calendar = Calendar.getInstance()
        val actualDay = calendar.get(Calendar.DAY_OF_MONTH)
        val actualMonth = calendar.get(Calendar.MONTH)
        val actualYear = calendar.get(Calendar.YEAR)

        calendar.time = date
        val givenDay = calendar.get(Calendar.DAY_OF_MONTH)
        val givenMonth = calendar.get(Calendar.MONTH)
        val givenYear = calendar.get(Calendar.YEAR)
        return givenYear < givenYear
            || (givenYear == actualYear && givenMonth < actualMonth)
            || (givenYear == actualYear && givenMonth == actualMonth && givenDay < actualDay)
    }
}