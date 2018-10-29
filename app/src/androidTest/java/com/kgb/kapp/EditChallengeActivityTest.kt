package com.kgb.kapp

import android.content.Context
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.widget.Spinner
import com.kgb.kapp.challenge.ChallengeType
import com.kgb.kapp.challenge.Constants
import com.kgb.kapp.challenge.StepProgress
import com.kgb.kapp.db.entity.ChallengeEntity
import com.kgb.kapp.di.module.RepositoryModule
import com.kgb.kapp.repository.ChallengesRepository
import com.kgb.kapp.rules.DaggerActivityTestRule
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Date

/**
 * Test for [EditChallengeActivity] class
 */
@RunWith(AndroidJUnit4::class)
class EditChallengeActivityTest {
    @Rule @JvmField
    var activityRule = DaggerActivityTestRule<EditChallengeActivity>(EditChallengeActivity::class.java)

    @Test
    fun checkCorrectChallengeName() {
        activityRule.repoModule = object : RepositoryModule(KApplication.instance) {
            override fun provideChallengesRepository(): ChallengesRepository {
                return MockChallengeRepository()
            }
        }
        val testChallenge = ChallengeEntity(1, ChallengeType.BRIDGE, 1, StepProgress.BEGINNER, Date(), 1, 10)
        val intent = Intent(InstrumentationRegistry.getTargetContext(), EditChallengeActivity::class.java)
        intent.putExtra(Constants.CHALLENGE_ITEM_ID_KEY, testChallenge.id)
        activityRule.launchActivity(intent)

        // check that spinner contains correct number of data
        onView(withId(R.id.challenge_name))
            .check(ViewAssertions.matches(isDisplayed()))
            .check(ViewAssertions.matches(withSpinnerCount(ChallengeType.values().size)))

        // check that challenge has proper name
        onView(withId(R.id.challenge_name))
            .check(ViewAssertions.matches(isDisplayed()))
            .check(ViewAssertions.matches(withSpinnerSelectedItem(InstrumentationRegistry.getTargetContext(), testChallenge.challengeName.challengeResId)))
    }

    private fun withSpinnerCount(size: Int): Matcher<in View>? {
        return object : BoundedMatcher<View, Spinner>(Spinner::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("expected size: $size doesn't fit")
            }

            override fun matchesSafely(item: Spinner?): Boolean {
                return item?.adapter?.count == size ?: false
            }

        }
    }

    private fun withSpinnerSelectedItem(context: Context, resId: Int): Matcher<in View>? {
        return object : BoundedMatcher<View, Spinner>(Spinner::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("expected selected item text: ${context.getString(resId)}")
            }

            override fun describeMismatch(item: Any?, description: Description?) {
                if (item is Spinner) {
                    val challenge = ChallengeType.values()[item.selectedItemPosition]
                    description?.appendText("actual selected item text: ${context.getString(challenge.challengeResId)}")
                }
                super.describeMismatch(item, description)
            }

            override fun matchesSafely(item: Spinner?): Boolean {
                return item?.let {
                    val challenge = ChallengeType.values()[it.selectedItemPosition]
                    val result = challenge.challengeResId == resId
                    result
                } ?: false

            }

        }
    }
}