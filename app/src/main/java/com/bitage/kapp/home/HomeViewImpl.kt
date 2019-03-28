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
import com.bitage.dsl.dayDiff
import com.bitage.dsl.format
import com.bitage.dsl.get
import com.bitage.kapp.model.UserInfo
import com.bitage.kapp.model.UserInfoType
import com.bitage.kapp.presentation.Constants
import com.bitage.naw_views.CalendarButton
import io.reactivex.functions.Consumer
import java.util.Calendar
import java.util.Date

/**
 * Implementation of home screen
 */
class HomeViewImpl : HomeView {

    private val binding: ChallengesMainBinding by lazy {
        DataBindingUtil.setContentView<ChallengesMainBinding>(screen.getActivity(), R.layout.challenges_main)
    }
    private lateinit var viewModel: HomeViewModel
    private lateinit var screen: Screen
    private val dateChangeObserver = Observer<Date> {
        binding.selectedDay.text = it format "dd MMM, ''yy"
        viewModel.getDayChallengesState(Consumer { p ->
            binding.roundProgress.setProgress(p.first, p.second)
        })
    }

    /**
     * Controls lifecycle of this view. It should be called in presenter onCreate method
     */
    override fun onCreate() {
    }

    override fun onResume() {
        binding.setLifecycleOwner(screen)

        viewModel.dateData.observe(screen, dateChangeObserver)
    }

    override fun onPause() {
        viewModel.dateData.removeObserver(dateChangeObserver)
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


            leftButton.setOnClickListener {
                val timeInMillis = calendarButton.getSelectedDate().time dayDiff -1
                calendarButton.setDate(timeInMillis, true)

            }
            rightButton.setOnClickListener {
                val timeInMillis = calendarButton.getSelectedDate().time dayDiff 1
                calendarButton.setDate(timeInMillis, true)
            }
            viewModel.dateData.postValue(calendarButton.getSelectedDate())
        }
    }

    /**
     * get the real android view
     */
    override fun androidView(): View = binding.root

    override fun customizeActionBar(actionBar: ActionBar?) {}

    override fun setUserInfo(userInfo: UserInfo) {
        binding.userInfoText.text = userInfo.get(UserInfoType.USERNAME)
    }

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
                val intent = Intent(screen.getActivity(), TodayChallengesActivity::class.java)
                intent.putExtra(Constants.CURRENT_DATE_DAY, date get Calendar.DAY_OF_MONTH)
                intent.putExtra(Constants.CURRENT_DATE_MONTH, date get Calendar.MONTH)
                intent.putExtra(Constants.CURRENT_DATE_YEAR, date get Calendar.YEAR)
                screen.startActivity(intent)
            }
        }
    }

    private fun isDateExpire(date: Date) : Boolean {
        val actualDay = date get Calendar.DAY_OF_MONTH
        val actualMonth = date get Calendar.MONTH
        val actualYear = date get Calendar.YEAR

        val givenDay = date get Calendar.DAY_OF_MONTH
        val givenMonth = date get Calendar.MONTH
        val givenYear = date get Calendar.YEAR
        return givenYear < actualYear
            || (givenYear == actualYear && givenMonth < actualMonth)
            || (givenYear == actualYear && givenMonth == actualMonth && givenDay < actualDay)
    }
}