package com.kgb.kapp

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.kgb.kapp.challenge.ChallengeType
import com.kgb.kapp.components.ComponentDAO
import com.kgb.kapp.challenge.StepProgress
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EditChallengeActivityTest {
    @Rule @JvmField
    var activityRule = ActivityTestRule<EditChallengeActivity>(EditChallengeActivity::class.java, true, false)
    private val testChallenge = ComponentDAO(ChallengeType.PUSHUP, 1, StepProgress.BEGINNER)

    @Test
    fun checkCorrectChallengeName() {
        val intent = Intent(InstrumentationRegistry.getTargetContext(), EditChallengeActivity::class.java)
        intent.putExtra(EditChallengeActivity.COMPONENT_DATA, testChallenge)
        activityRule.launchActivity(intent)

        //check that challenge has proper name
        onView(withId(R.id.challenge_name))
            .check(matches(isDisplayed()))
            .check(matches(withText(testChallenge.challengeType.challengeName)))
    }
}