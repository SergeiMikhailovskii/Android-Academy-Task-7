package com.mikhailovskii.exercise7.ui.main;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.mikhailovskii.exercise7.R;
import com.mikhailovskii.exercise7.ui.adapter.ViewPagerAdapter;

public class MainActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.view_pager);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

    }

}
