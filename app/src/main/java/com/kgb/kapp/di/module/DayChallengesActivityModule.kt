package com.kgb.kapp.di.module

import android.arch.lifecycle.ViewModelProviders
import com.kgb.kapp.TodayChallengesActivity
import com.kgb.kapp.challenge.Constants
import com.kgb.kapp.repository.ChallengesRepository
import com.kgb.kapp.viewmodel.DayChallengeViewModel
import com.kgb.kapp.viewmodel.DayViewModelFactory
import dagger.Module
import dagger.Provides
import java.util.Date
import java.util.Calendar

@Module
class DayChallengesActivityModule(private val activity: TodayChallengesActivity) {
    @Provides
    fun provideViewModel(repository: ChallengesRepository): DayChallengeViewModel {
        val date = getCurrentDate(activity)
        return ViewModelProviders.of(activity, DayViewModelFactory(date, repository)).get(DayChallengeViewModel::class.java)
    }

    private fun getCurrentDate(activity: TodayChallengesActivity): Date {
        return activity.intent?.extras?.let { extras ->
            val day = extras.getInt(Constants.CURRENT_DATE_DAY)
            val month = extras.getInt(Constants.CURRENT_DATE_MONTH)
            val year = extras.getInt(Constants.CURRENT_DATE_YEAR)
            val calendar = Calendar.getInstance()
            calendar.set(year, month, day)
            calendar.time
        } ?: Date()
    }
}