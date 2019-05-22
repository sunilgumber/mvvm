package com.ameba.harness.swipe.view.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

import com.ameba.harness.swipe.R;
import com.ameba.harness.swipe.util.CustomLog;
import com.ameba.harness.swipe.view.listener.backpress.OnBackPressedListener;
import com.ameba.harness.swipe.util.Constants;
import com.ameba.harness.swipe.view.listener.event.EventBus;
import com.ameba.harness.swipe.view.listener.VerticalPageListener;
import com.ameba.harness.swipe.viewpager.VerticalPager;
import com.google.android.gms.maps.model.LatLng;

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
		snapPageWhenLayoutIsReady(mVerticalPager);
	}

	//public VerticalPager FindViewById(int Id) {}
	public void movetocenter(){
		mVerticalPager.setScrollingEnabled(true);
		mVerticalPager.scrollDown();
	}

	private void snapPageWhenLayoutIsReady(final View pageView) {
		/*
		 * VerticalPager is not fully initialized at the moment, so we want to snap to the central page only when it
		 * layout and measure all its pages.
		 */
		pageView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onGlobalLayout() {
				mVerticalPager.snapToPage(MainActivity.CENTRAL_PAGE_INDEX, VerticalPager.PAGE_SNAP_DURATION_INSTANT);

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
			CustomLog.error(e.toString());
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
	}
}
