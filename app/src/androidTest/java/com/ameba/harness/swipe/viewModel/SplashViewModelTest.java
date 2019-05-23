package com.ameba.harness.swipe.viewModel;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.content.ContextCompat;

import com.ameba.harness.swipe.util.CustomLog;
import com.ameba.harness.swipe.view.activity.SplashActivity;
import com.ameba.harness.swipe.view.listener.CompletedListener;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ameba on 23/5/19.
 */
public class SplashViewModelTest {

    public SplashActivity splashActivity;
    CompletedListener completedListener;
    SplashViewModel viewModel;
    @Rule
    public ActivityTestRule<SplashActivity> activityActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Before
    public void setUp() throws Exception {
        splashActivity = activityActivityTestRule.getActivity();
        viewModel=new SplashViewModel(splashActivity,completedListener);
    }

    @After
    public void tearDown() throws Exception {
        splashActivity=null;
    }

//positive test case
    @Test
    public void checkLocationPermission() throws Exception {
        boolean b=ContextCompat.checkSelfPermission(splashActivity.getBaseContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
        CustomLog.error(String.valueOf(b)+" "+ viewModel.checkLocationPermission());
        assertEquals(b, viewModel.checkLocationPermission());
    }


}