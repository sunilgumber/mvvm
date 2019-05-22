package com.ameba.harness.swipe.view.activity;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.ameba.harness.swipe.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ameba on 4/5/19.
 */
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    public MainActivity mainActivity;

    @Before
    public void setUp() throws Exception {
        mainActivity = activityActivityTestRule.getActivity();
    }


    @Test
    public void TlViewPager() {
        View view = mainActivity.findViewById(R.id.activity_main_vertical_pager);
        assertNotNull(view);
    }


    @Test
    public void TlFragmentMainCentral() {
        View view = mainActivity.findViewById(R.id.main_central_fragment);
        assertNotNull(view);
    }

    @Test
    public void TlFragmentMainTop() {
        View view = mainActivity.findViewById(R.id.main_top_fragment);
        assertNotNull(view);
    }

    @Test
    public void TlFragmentMainBottom() {
        View view = mainActivity.findViewById(R.id.main_bottom_fragment);
        assertNotNull(view);
    }

    @Test
    public void TlFragmentMainLeft() {
        View view = mainActivity.findViewById(R.id.fragmentleft);
        assertNotNull(view);
    }

    @Test
    public void TlFragmentMainRight() {
        View view = mainActivity.findViewById(R.id.fragmentRight);
        assertNotNull(view);
    }

    @Test
    public void TlCentreSwipeAllSides() {
        Espresso.onView(ViewMatchers.withId(R.id.main_central_fragment)).perform(ViewActions.swipeRight());
        Espresso.onView(ViewMatchers.withId(R.id.main_central_fragment)).perform(ViewActions.swipeLeft());
        Espresso.onView(ViewMatchers.withId(R.id.main_central_fragment)).perform(ViewActions.swipeDown());
        Espresso.onView(ViewMatchers.withId(R.id.main_central_fragment)).perform(ViewActions.swipeUp());
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}