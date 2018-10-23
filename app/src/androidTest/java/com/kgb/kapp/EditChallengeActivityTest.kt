package com.kgb.kapp

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test for [EditChallengeActivity] class
 */
@RunWith(AndroidJUnit4::class)
class EditChallengeActivityTest {
    @Rule @JvmField
    var activityRule = ActivityTestRule<EditChallengeActivity>(EditChallengeActivity::class.java, true, false)

    @Test
    fun checkCorrectChallengeName() {
//        val intent = Intent(InstrumentationRegistry.getTargetContext(), EditChallengeActivity::class.java)
//        intent.putExtra(EditChallengeActivity.COMPONENT_DATA, testChallenge)
//        activityRule.launchActivity(intent)
//
//        // check that challenge has proper name
//        onView(withId(R.id.challenge_name))
//            .check(matches(isDisplayed()))
//            .check(matches(withText(testChallenge.challengeType.challengeName)))
    }
}