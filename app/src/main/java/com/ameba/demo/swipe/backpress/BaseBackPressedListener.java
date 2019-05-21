package com.ameba.demo.swipe.backpress;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.ameba.demo.swipe.activity.MainActivity;
import com.ameba.demo.swipe.constants.Constants;


public class BaseBackPressedListener implements OnBackPressedListener{
    private final FragmentActivity activity;
    private MainActivity homeActivity;
    public BaseBackPressedListener(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void doBack(String fragmentValue) {
        homeActivity= (MainActivity) activity;
        Bundle bundle;
        switch (fragmentValue){
            case Constants.FragMaps:
                homeActivity.movetocenter();
                break;
            default:
                homeActivity.finish();
                break;
        }
    }
}
