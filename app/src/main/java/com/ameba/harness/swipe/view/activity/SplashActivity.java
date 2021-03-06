package com.ameba.harness.swipe.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.ameba.harness.swipe.R;
import com.ameba.harness.swipe.view.listener.CompletedListener;
import com.ameba.harness.swipe.viewModel.SplashViewModel;

public class SplashActivity extends Activity implements CompletedListener {
    private boolean isGPS;
    private SplashViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        viewModel = new SplashViewModel(this, this);
        viewModel.SwitchMainActivity();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        viewModel.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewModel.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCompleted() {
        GotoMainActivity();
    }

    @Override
    public void onFailed() {
        finish();
    }

    private void GotoMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
