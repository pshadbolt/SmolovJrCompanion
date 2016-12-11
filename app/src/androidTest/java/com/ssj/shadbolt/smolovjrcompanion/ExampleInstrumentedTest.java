package com.ssj.shadbolt.smolovjrcompanion;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ssj.shadbolt.smolovjrcompanion.ui.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.hasToString;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void clearDatabase(){
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText(R.string.action_database)).perform(click());
        onView(withId(R.id.action_delete)).perform(click());
        onView(withText(R.string.button_delete)).perform(click());
    }

    @Test
    public void selectWorkout() {
        onView(withId(R.id.train)).perform(click());
        onView(withId(R.id.hit)).perform(click());
        onView(withId(R.id.hit)).perform(click());
        onView(withId(R.id.hit)).perform(click());
        onView(withId(R.id.action_save)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.grid_view)).atPosition(0).perform(click());
        //onData(hasToString(startsWith("DAY 01"))).inAdapterView(withId(R.id.grid_view)).perform(click());
    }
}
