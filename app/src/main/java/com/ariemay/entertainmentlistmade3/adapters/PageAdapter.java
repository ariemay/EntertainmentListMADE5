package com.ariemay.entertainmentlistmade3.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ariemay.entertainmentlistmade3.fragments.MoviesFragment;
import com.ariemay.entertainmentlistmade3.fragments.TVListFragment;

public class PageAdapter extends FragmentStatePagerAdapter {

    private int mNoOfTabs;

    public PageAdapter(FragmentManager fm, int NumberOfTabs) {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                //MovieFragment fragment activity
                return new MoviesFragment();
            case 1:
                //TvFragment fragment activity
                return new TVListFragment();
            default:
                return null;
        }
    }

    @Override
    // get item count
    public int getCount() {
        return mNoOfTabs;
    }
}