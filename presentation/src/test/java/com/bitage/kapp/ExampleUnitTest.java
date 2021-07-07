package com.bitage.kapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private static final int ADD_RESULT_EXAMPLE = 4;
    /**
     * example of unit test
     */
    @Test
    public void additionIsCorrect() {
        assertEquals(ADD_RESULT_EXAMPLE, 2 + 2);
    }
}