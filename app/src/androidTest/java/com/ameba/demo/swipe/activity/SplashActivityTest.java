package com.ameba.demo.swipe.activity;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.ameba.demo.swipe.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by ameba on 4/5/19.
 */
public class SplashActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> activityActivityTestRule = new ActivityTestRule<SplashActivity>(SplashActivity.class);

    public SplashActivity splashActivity;

    @Before
    public void setUp() throws Exception {
        splashActivity = activityActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        splashActivity = null;
    }

    @Test
    public void TlViewPager() {
        splashActivity.SwitchMainActivity();
    }


}