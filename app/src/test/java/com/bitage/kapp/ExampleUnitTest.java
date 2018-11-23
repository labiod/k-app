package com.bitage.kapp;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private static final int ADD_RESULT_EXAMPLE = 4;

    @Rule
    public TestName rule = new TestName();

    @Before
    public void setup() {
        System.out.println(rule.getMethodName());
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(ADD_RESULT_EXAMPLE, 2 + 2);
    }
}