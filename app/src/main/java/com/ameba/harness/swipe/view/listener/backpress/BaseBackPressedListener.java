package com.ameba.harness.swipe.view.listener.backpress;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.ameba.harness.swipe.view.activity.MainActivity;
import com.ameba.harness.swipe.util.Constants;


public class BaseBackPressedListener implements OnBackPressedListener{
    private final FragmentActivity activity;

    public BaseBackPressedListener(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void doBack(String fragmentValue) {
        MainActivity homeActivity = (MainActivity) activity;
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
