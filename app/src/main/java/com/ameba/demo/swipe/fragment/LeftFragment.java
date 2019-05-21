package com.ameba.demo.swipe.fragment;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ameba.demo.swipe.R;
import com.ameba.demo.swipe.inteface.LocationUpdateListener;
import com.ameba.demo.swipe.util.CustomLog;

/**
 * Fragment to manage the left page of the 5 pages application navigation (top, center, bottom, left, right).
 */
public class LeftFragment extends Fragment {

	// -----------------------------------------------------------------------
	//
	// Methods
	//
	// -----------------------------------------------------------------------
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View fragmentView = inflater.inflate(R.layout.fragment_left, container, false);
		return fragmentView;
	}

}
