package com.ssj.shadbolt.smolovjrcompanion;

import com.ssj.shadbolt.smolovjrcompanion.model.Workout;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void plateDiagram() throws Exception {

        Workout workout = new Workout();
        workout.setWeight(300);
        System.out.println(workout.getPlateDiagram());
        assertEquals(4, 2 + 2);
    }
}