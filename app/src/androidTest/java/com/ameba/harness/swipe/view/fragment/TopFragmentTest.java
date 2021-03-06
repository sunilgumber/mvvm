package com.ameba.harness.swipe.view.fragment;

import android.content.Context;
import android.location.Location;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.test.mock.MockContext;

import com.ameba.harness.swipe.R;
import com.ameba.harness.swipe.view.activity.MainActivity;
import com.ameba.harness.swipe.util.GpsUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Created by ameba on 4/5/19.
 */
public class TopFragmentTest {
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    public MainActivity mainActivity;
    private TopFragment fragment;


    @Before
    public void setUp() throws Exception {
        mainActivity = activityActivityTestRule.getActivity();
        Context context = new MockContext();
        onView(ViewMatchers.withId(R.id.main_central_fragment)).perform(ViewActions.swipeDown());
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }


    @Test
    public void TlFragTopVisible() {
        onView(withId(R.id.main_top_fragment)).check(matches(isDisplayed()));
    }

    @Test
    public void TlFragTopTextView() {
        onView(withId(R.id.tlRight)).check(matches(withText(R.string.right)));
    }

    //positive test case
    @Test
    public void IsValidLocation(){
        Location targetLocation = new Location("");//provider name is unnecessary
        targetLocation.setLatitude(89.0d);//your coords of course
        targetLocation.setLongitude(179.0d);
        assertEquals(true,GpsUtils.isvalidcoordinates(targetLocation));
    }
    //negative test case
    @Test
    public void NIsValidLocation(){
        Location targetLocation = new Location("");//provider name is unnecessary
        targetLocation.setLatitude(0.0d);//your coords of course
        targetLocation.setLongitude(0.0d);
        assertEquals(true,GpsUtils.isvalidcoordinates(targetLocation));
    }


}