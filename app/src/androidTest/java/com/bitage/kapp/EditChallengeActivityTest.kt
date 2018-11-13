package com.bitage.kapp

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Spinner
import com.bitage.kapp.challenge.ChallengeTypeResMapper
import com.bitage.kapp.db.ChallengeDB
import com.bitage.kapp.model.ChallengeType
import com.bitage.kapp.model.StepProgress
import com.bitage.kapp.di.module.RepositoryModule
import com.bitage.kapp.editchallenge.EditChallengeActivity
import com.bitage.kapp.entity.ChallengeEntity
import com.bitage.kapp.entity.UserProgressEntity
import com.bitage.kapp.presentation.Constants
import com.bitage.kapp.repository.ChallengeRepository
import com.bitage.kapp.repository.MockChallengeRepository
import com.bitage.kapp.rules.DaggerActivityTestRule
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Before
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

    lateinit var callIntent: Intent

    /**
     * Method to setup test
     */
    @Before
    fun setupTest() {
        activityRule.repoModule = object : RepositoryModule(KApplication.instance) {
            override fun provideChallengesRepository(db: ChallengeDB): ChallengeRepository {
                return MockChallengeRepository()
            }
        }
        callIntent = Intent(InstrumentationRegistry.getTargetContext(), EditChallengeActivity::class.java)
    }

    /**
     * Check that titel in view is correct for new challenge
     */
    @Test
    fun checkCorrectTitleForNewChallenge() {
        activityRule.launchActivity(callIntent)

        // check that spinner contains correct number of data
        onView(isAssignableFrom(Toolbar::class.java))
                .check(matches(isDisplayed()))
                .check(matches(withTitle(InstrumentationRegistry.getTargetContext().getString(R.string.new_challenge_title))))
    }

    /**
     * Check that titel in view is correct for edit challenge
     */
    @Test
    fun checkCorrectTitleForEditChallenge() {
        val testChallenge = ChallengeEntity(1, ChallengeType.BRIDGE, 1, StepProgress.BEGINNER, Date(), 1, 10)
        callIntent.putExtra(Constants.CHALLENGE_ITEM_ID_KEY, testChallenge.id)
        activityRule.launchActivity(callIntent)

        // check that spinner contains correct number of data
        onView(isAssignableFrom(Toolbar::class.java))
                .check(matches(isDisplayed()))
                .check(matches(withTitle(InstrumentationRegistry.getTargetContext().getString(R.string.edit_challenge_title))))
    }

    /**
     * Check that current view was init correctly
     */
    @Test
    fun checkThatViewWasInitCorrectly() {
        activityRule.launchActivity(callIntent)

        // check that challenge name spinner is displayed and contains correct number of data
        onView(withId(R.id.challenge_name))
                .check(matches(isDisplayed()))
                .check(matches(withSpinnerCount(ChallengeType.values().size)))

        // check that challenge step spinner is displayed and contains correct number of data
        onView(withId(R.id.challenge_step))
                .check(matches(isDisplayed()))
                .check(matches(withSpinnerCount(10)))

        // check that challenge progress spinner is displayed and contains correct number of data
        onView(withId(R.id.challenge_step_progress))
                .check(matches(isDisplayed()))
                .check(matches(withSpinnerCount(StepProgress.values().size)))

        // check that challenge goal is displayed
        onView(withId(R.id.challenge_goal))
                .check(matches(isDisplayed()))

        // check that challenge series is displayed
        onView(withId(R.id.challenge_series))
                .check(matches(isDisplayed()))
    }

    /**
     * Check That all view was set to default value for new challenge
     */
    @Test
    fun checkDataSetToDefaultValueForNewChallenge() {
        activityRule.launchActivity(callIntent)

        val defaultChallengeType = ChallengeType.PUSHUP

        val defaultValues = UserProgressEntity.createNew(defaultChallengeType)

        // check that challenge name has proper value
        onView(withId(R.id.challenge_name))
                .check(matches(isDisplayed()))
                .check(matches(withSpinnerSelectedItem(InstrumentationRegistry.getTargetContext()
                    .getString(ChallengeTypeResMapper.valueOf(defaultChallengeType.name).resId))))

        // check that challenge step has proper value
        onView(withId(R.id.challenge_step))
                .check(matches(isDisplayed()))
                .check(matches(withSpinnerSelectedItem(defaultValues.step.toString())))

        // check that challenge progress has proper value
        onView(withId(R.id.challenge_step_progress))
                .check(matches(isDisplayed()))
                .check(matches(withSpinnerSelectedItem(defaultValues.stepProgress.toString())))

        // check that challenge goal has proper value
        onView(withId(R.id.challenge_goal))
                .check(matches(isDisplayed()))
                .check(matches(withText(defaultValues.goal.toString())))

        // check that challenge series has proper value
        onView(withId(R.id.challenge_series))
                .check(matches(isDisplayed()))
                .check(matches(withText(defaultValues.series.toString())))
    }

    /**
     * Check that correct date was set when user want to edit challenge
     */
    @Test
    fun checkCorrectDataWhenChallengeEdited() {
        val testChallenge = ChallengeEntity(1, ChallengeType.BRIDGE, 1, StepProgress.BEGINNER, Date(), 1, 10)
        callIntent.putExtra(Constants.CHALLENGE_ITEM_ID_KEY, testChallenge.id)
        activityRule.launchActivity(callIntent)

        // check that challenge name has proper value
        onView(withId(R.id.challenge_name))
                .check(matches(isDisplayed()))
                .check(matches(withSpinnerSelectedItem(InstrumentationRegistry.getTargetContext()
                    .getString(ChallengeTypeResMapper.valueOf(testChallenge.challengeName.name).resId))))

        // check that challenge step has proper value
        onView(withId(R.id.challenge_step))
                .check(matches(isDisplayed()))
                .check(matches(withSpinnerSelectedItem(testChallenge.step.toString())))

        // check that challenge progress has proper value
        onView(withId(R.id.challenge_step_progress))
                .check(matches(isDisplayed()))
                .check(matches(withSpinnerSelectedItem(testChallenge.progress.toString())))

        // check that challenge goal has proper value
        onView(withId(R.id.challenge_goal))
                .check(matches(isDisplayed()))
                .check(matches(withText(testChallenge.goal.toString())))

        // check that challenge series has proper value
        onView(withId(R.id.challenge_series))
                .check(matches(isDisplayed()))
                .check(matches(withText(testChallenge.series.toString())))
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

    private fun withSpinnerSelectedItem(text: String): Matcher<in View>? {
        return object : BoundedMatcher<View, Spinner>(Spinner::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("expected selected item text: $text")
            }

            override fun matchesSafely(item: Spinner?): Boolean {
                return item?.let {
                    val challenge = it.selectedItem.toString()
                    val result = challenge.equals(text)
                    result
                } ?: false
            }
        }
    }

    private fun withTitle(expectedTitle: CharSequence): Matcher<View> {
        return object : BoundedMatcher<View, Toolbar>(Toolbar::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("with toolbar title: $expectedTitle")
            }

            override fun matchesSafely(toolbar: Toolbar?): Boolean {
                return expectedTitle == toolbar?.title
            }
        }
    }
}