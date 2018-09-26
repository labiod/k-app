package com.kgb.kapp.database;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.kgb.kapp.data.ChallengeDataSource;
import com.kgb.kapp.data.YourChallengeDataSource;
import com.kgb.kapp.model.Challenge;
import com.kgb.kapp.model.YourChallenge;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class DBInstrumentedTest {

    @Test
    public void test1_clear_challenges() {
        Context context = InstrumentationRegistry.getTargetContext();
        DBConnection connection = new DBConnection(new ChallengeDBHelper(context));
        ChallengeDataSource challengeDataSource = new ChallengeDataSource(connection);
        List<Challenge> challengeList = challengeDataSource.retrieveData();
        for (Challenge challenge : challengeList) {
            assertEquals(1, connection.delete(challenge));
        }
    }

    @Test
    public void test2_insertChallenge() {
        Context context = InstrumentationRegistry.getTargetContext();
        DBConnection connection = new DBConnection(new ChallengeDBHelper(context));
        Challenge challenge = new Challenge("Test1", 150, 1);
        long id = connection.insert(challenge);
        assertTrue(id != -1);
    }

    @Test
    public void test3_selectChallenges() {
        Context context = InstrumentationRegistry.getTargetContext();
        DBConnection connection = new DBConnection(new ChallengeDBHelper(context));
        ChallengeDataSource challengeDataSource = new ChallengeDataSource(connection);
        List<Challenge> challengeList = challengeDataSource.retrieveData();
        assertEquals(1, challengeList.size());

        for (Challenge challenge : challengeList) {
            System.out.println(challenge.getName());
            assertEquals("Test1", challenge.getName());
        }
    }

    @Test
    public void test4_addYourChallenge() {
        Context context = InstrumentationRegistry.getTargetContext();
        DBConnection connection = new DBConnection(new ChallengeDBHelper(context));
        ChallengeDataSource challengeDataSource = new ChallengeDataSource(connection);
        List<Challenge> challenges = challengeDataSource.retrieveData(ChallengeContract.ChallengeEntry.COLUMN_RATE+ " > 100");
        assertTrue(challenges.size() > 0);
        YourChallengeDataSource yourChallengeDataSource = new YourChallengeDataSource(connection);
        YourChallenge yourChallenge = new YourChallenge(challenges.get(0));
//        yourChallenge.setStartDate();
    }
}
