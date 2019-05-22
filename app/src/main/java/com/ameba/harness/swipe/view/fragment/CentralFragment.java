package com.ameba.harness.swipe.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ameba.harness.swipe.R;
import com.ameba.harness.swipe.view.listener.event.DoubleClickListener;


/**
 * Fragment to manage the central page of the 5 pages application navigation (top, center, bottom, left, right).
 */
public class CentralFragment extends Fragment {

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
