package com.mikhailovskii.exercise7.ui.main;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mikhailovskii.exercise7.R;
import com.mikhailovskii.exercise7.ui.adapter.PhotosAdapter;

public class MainActivity extends FragmentActivity implements MainContract.MainView {

    private MainPresenter mPresenter = new MainPresenter();

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter.attachView(this);

        viewPager = findViewById(R.id.view_pager);
        pagerAdapter = new PhotosAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mPresenter.loadPhotoList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void onPhotosLoaded(String url, String description) {

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
