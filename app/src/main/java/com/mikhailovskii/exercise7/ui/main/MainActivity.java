package com.mikhailovskii.exercise7.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mikhailovskii.exercise7.R;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onPhotosLoaded() {

    }

    @Override
    public void onPhotosLoadingFailed() {

    }

    @Override
    public void showEmptyState(boolean value) {

    }

    @Override
    public void showLoadingIndicator(boolean value) {

    }
}
