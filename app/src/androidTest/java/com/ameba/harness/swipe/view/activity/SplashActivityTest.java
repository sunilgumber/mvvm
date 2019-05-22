package com.ameba.harness.swipe.view.activity;

import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by ameba on 4/5/19.
 */
public class SplashActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> activityActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

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