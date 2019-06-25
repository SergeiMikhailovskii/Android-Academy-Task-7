package com.mikhailovskii.exercise7.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mikhailovskii.exercise7.ui.page.PageFragment;

public class PhotosAdapter extends FragmentPagerAdapter {

    public static final int PHOTOS_COUNT = 4;

    public PhotosAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return PageFragment.newInstance(i);
    }

    @Override
    public int getCount() {
        return PHOTOS_COUNT;
    }


}
