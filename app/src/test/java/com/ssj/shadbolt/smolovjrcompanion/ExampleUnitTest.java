package com.ssj.shadbolt.smolovjrcompanion;

import com.ssj.shadbolt.smolovjrcompanion.model.SmolovJrUtil;

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
        System.out.println(SmolovJrUtil.getPlateDiagram(300));
        assertEquals(4, 2 + 2);
    }
}