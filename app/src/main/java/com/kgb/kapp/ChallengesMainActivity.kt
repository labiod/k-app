package com.kgb.kapp

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kgb.kapp.challenge.Constants
import com.kgb.kapp.databinding.ChallengesMainBinding
import kotlinx.android.synthetic.main.challenges_main.*

/**
 * Activity screen to show calendar view
 */
class ChallengesMainActivity : AppCompatActivity() {
    private lateinit var binding: ChallengesMainBinding

    /**
     * Method call by android when create activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.challenges_main)
        initCalendar()
    }

    private fun initCalendar() {
        challenges_calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            android.util.Log.d("KGB", "Clicked on calendar")
            val intent = Intent(this, TodayChallengesActivity::class.java)
            intent.putExtra(Constants.CURRENT_DATE_DAY, dayOfMonth)
            intent.putExtra(Constants.CURRENT_DATE_MONTH, month)
            intent.putExtra(Constants.CURRENT_DATE_YEAR, year)
            startActivity(intent)
        }
    }
}