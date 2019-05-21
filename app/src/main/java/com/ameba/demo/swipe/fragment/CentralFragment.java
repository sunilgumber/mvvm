package com.ameba.demo.swipe.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.ameba.demo.swipe.R;
import com.ameba.demo.swipe.activity.MainActivity;
import com.ameba.demo.swipe.event.DoubleClickListener;


/**
 * Fragment to manage the central page of the 5 pages application navigation (top, center, bottom, left, right).
 */
public class CentralFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View fragmentView = inflater.inflate(R.layout.fragment_central, container, false);
		final RelativeLayout relativeLayout= (RelativeLayout) (fragmentView).findViewById(R.id.rlcentral);
		relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View view) {
				relativeLayout.setBackgroundColor(Color.GRAY);
				return true;
			}
		});
		relativeLayout.setOnClickListener(new DoubleClickListener() {
			@Override
			public void onSingleClick(View v) {
				relativeLayout.setBackgroundColor(Color.WHITE);
			}

			@Override
			public void onDoubleClick(View v) {
				relativeLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue));
			}
		});
		return fragmentView;
	}

}
