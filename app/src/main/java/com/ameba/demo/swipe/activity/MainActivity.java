package com.ameba.demo.swipe.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

import com.ameba.demo.swipe.R;
import com.ameba.demo.swipe.backpress.OnBackPressedListener;
import com.ameba.demo.swipe.constants.Constants;
import com.ameba.demo.swipe.event.EventBus;
import com.ameba.demo.swipe.inteface.VerticalPageListener;
import com.ameba.demo.swipe.view.VerticalPager;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

import io.nlopez.smartlocation.SmartLocation;

/**
 * Manages start screen of the application.
 */
public class MainActivity extends FragmentActivity {

	// -----------------------------------------------------------------------
	//
	// Statics
	//
	// -----------------------------------------------------------------------
	/**
	 * Start page index. 0 - top page, 1 - central page, 2 - bottom page.
	 */
	public String fragmentValue;
	private static final int CENTRAL_PAGE_INDEX = 1;
	public LatLng currentlatlng;
	// -----------------------------------------------------------------------
	//
	// Fields
	//
	// -----------------------------------------------------------------------
	public VerticalPager mVerticalPager;
	protected OnBackPressedListener onBackPressedListener;

	private VerticalPageListener verticalPageListener=new VerticalPageListener() {
		@Override
		public void pageselected(int pageno) {
			switch (pageno){
				case 0:
				fragmentValue= Constants.FragMaps;
				break;
				case 1:
				fragmentValue= Constants.FragCentral;
				break;
				case 2:
				fragmentValue= Constants.FragBottom;
				break;
			}
		}
	};
	// -----------------------------------------------------------------------
	//
	// Methods
	//
	// -----------------------------------------------------------------------
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViews();
	}

	private void findViews() {
		mVerticalPager = (VerticalPager) findViewById(R.id.activity_main_vertical_pager);
		mVerticalPager.setPageCallback(verticalPageListener);
		initViews();
	}
	private void initViews() {
		snapPageWhenLayoutIsReady(mVerticalPager, CENTRAL_PAGE_INDEX);
	}

	//public VerticalPager FindViewById(int Id) {}
	public void movetocenter(){
		mVerticalPager.setScrollingEnabled(true);
		mVerticalPager.scrollDown();
	}

	private void snapPageWhenLayoutIsReady(final View pageView, final int page) {
		/*
		 * VerticalPager is not fully initialized at the moment, so we want to snap to the central page only when it
		 * layout and measure all its pages.
		 */
		pageView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onGlobalLayout() {
				mVerticalPager.snapToPage(page, VerticalPager.PAGE_SNAP_DURATION_INSTANT);

				if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
					// recommended removeOnGlobalLayoutListener method is available since API 16 only
					pageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				else
					removeGlobalOnLayoutListenerForJellyBean(pageView);
			}

			@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
			private void removeGlobalOnLayoutListenerForJellyBean(final View pageView) {
				pageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
			}
		});
	}


	public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
		this.onBackPressedListener = onBackPressedListener;
	}


	@Override
	protected void onResume() {
		super.onResume();
		EventBus.getInstance().register(this);
	}

	@Override
	protected void onPause() {
		EventBus.getInstance().unregister(this);
		super.onPause();
	}

	@Override
	public void onBackPressed() {
		try {
			try {
				if (onBackPressedListener != null) {
					onBackPressedListener.doBack(fragmentValue);
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {

		}
	}

	@Override
	protected void onStop() {
		super.onStop();
	}
}
