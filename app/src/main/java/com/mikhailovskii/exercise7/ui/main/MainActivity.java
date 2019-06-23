package com.mikhailovskii.exercise7.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mikhailovskii.exercise7.R;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    private ImageView mIvPhoto;
    private MainPresenter mPresenter = new MainPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter.attachView(this);

        mIvPhoto = findViewById(R.id.iv_photo);
        mPresenter.loadPhotoList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void onPhotosLoaded(String url, String description) {
        Glide.with(getApplicationContext())
                .load(url)
                .into(mIvPhoto);
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
