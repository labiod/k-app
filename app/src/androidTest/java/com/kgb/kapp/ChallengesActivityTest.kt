package com.kgb.kapp

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.Toolbar
import android.view.View
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChallengesActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(ChallengesActivity::class.java)

    @Test
    fun testChallengeActivityActionBar() {
        activityRule.launchActivity(Intent(InstrumentationRegistry.getTargetContext(), ChallengesActivity::class.java))
        val challengeTitle = InstrumentationRegistry.getTargetContext().getString(R.string.app_name)

        //check that activity has correct title
        onView(isAssignableFrom(Toolbar::class.java))
            .check(matches(withTitle(challengeTitle)))
    }


    @Test
    fun testChallengePlusButton() {
        activityRule.launchActivity(Intent(InstrumentationRegistry.getTargetContext(), ChallengesActivity::class.java))

        //check that button is displayed
        onView(withId(R.id.fab))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testChallengeClearButton() {
        activityRule.launchActivity(Intent(InstrumentationRegistry.getTargetContext(), ChallengesActivity::class.java))

        //check that button is gone when challenges list is zero
        onView(withId(R.id.challenge_name))
            .check(matches(not(isDisplayed())))

        onView(withId(R.id.fab))
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