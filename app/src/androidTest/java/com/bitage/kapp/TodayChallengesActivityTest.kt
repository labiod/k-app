package com.bitage.kapp

import android.content.Intent
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.runner.AndroidJUnit4
import androidx.appcompat.widget.Toolbar
import android.view.View
import com.bitage.kapp.daychallenges.TodayChallengesActivity
import com.bitage.kapp.presentation.Constants
import com.bitage.kapp.rules.DaggerActivityTestRule
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * Test for [TodayChallengesActivity] class
 */
@RunWith(AndroidJUnit4::class)
class TodayChallengesActivityTest {

    @Rule
    @JvmField
    var activityRule = DaggerActivityTestRule(TodayChallengesActivity::class.java)

    private val calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat("EEE MMM d, ''yy", Locale.getDefault())

    init {
        calendar.set(Calendar.DAY_OF_MONTH, 2)
        calendar.set(Calendar.MONTH, 11)
        calendar.set(Calendar.YEAR, 18)
    }

    /**
     * Test that title in current view was set correctly
     */
    @Test
    fun testChallengeActivityActionBar() {
        activityRule.launchActivity(Intent(InstrumentationRegistry.getTargetContext(), TodayChallengesActivity::class.java))
        val challengeTitle = InstrumentationRegistry.getTargetContext().getString(R.string.app_name)

        // check that activity has correct title
        onView(isAssignableFrom(Toolbar::class.java))
            .check(matches(withTitle(challengeTitle)))
    }

    /**
     * Test that plus button was init correctly
     */
    @Test
    fun testChallengePlusButton() {
        activityRule.launchActivity(Intent(InstrumentationRegistry.getTargetContext(), TodayChallengesActivity::class.java))

        // check that button is displayed
        onView(withId(R.id.fab))
            .check(matches(isDisplayed()))
    }

    /**
     * Test that view was init correctly
     */
    @Test
    fun testDayChallengeViewInitCorrectly() {
        val intent = Intent(InstrumentationRegistry.getTargetContext(), TodayChallengesActivity::class.java)

        intent.putExtra(Constants.CURRENT_DATE_DAY, calendar.get(Calendar.DAY_OF_MONTH))
        intent.putExtra(Constants.CURRENT_DATE_MONTH, calendar.get(Calendar.MONTH))
        intent.putExtra(Constants.CURRENT_DATE_YEAR, calendar.get(Calendar.YEAR))
        activityRule.launchActivity(intent)

        val dateText = dateFormat.format(calendar.time)

        // check that date was init correctly
        onView(withId(R.id.todayDateView))
            .check(matches(isDisplayed()))
            .check(matches(withText(dateText)))
    }

    private fun withTitle(expectedTitle: CharSequence): Matcher<View> {
        return object : BoundedMatcher<View, Toolbar>(Toolbar::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with toolbar title: $expectedTitle")
            }

            override fun matchesSafely(item: Toolbar?): Boolean {
                return item != null && expectedTitle == item.title
            }
        }
    }
}