package com.mikhailovskii.exercise7.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mikhailovskii.exercise7.ui.photo_collection.PhotoCollectionFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static final int PHOTOS_COUNT = 10;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return PhotoCollectionFragment.newInstance(i);
    }

    @Override
    public int getCount() {
        return PHOTOS_COUNT;
    }


}
