package com.ameba.harness.swipe.view.fragment;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.ameba.harness.swipe.R;
import com.ameba.harness.swipe.view.activity.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by ameba on 4/5/19.
 */
public class BottomFragmentTest {
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    public MainActivity mainActivity;


    @Before
    public void setUp() throws Exception {
        mainActivity = activityActivityTestRule.getActivity();
        onView(ViewMatchers.withId(R.id.main_central_fragment)).perform(ViewActions.swipeUp());
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }

    @Test
    public void TlFragLeftVisible() {
        onView(withId(R.id.main_bottom_fragment)).check(matches(isDisplayed()));
    }

    @Test
    public void TlFragTopTextView() {
        onView(withId(R.id.tlbottom)).check(matches(withText(R.string.bottom)));
    }

}