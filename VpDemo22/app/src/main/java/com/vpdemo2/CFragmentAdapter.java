package com.vpdemo2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by peterwu on 16/4/27.
 * <p/>
 * Fragment适配器
 */
public class CFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public CFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public CFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int arg0) {
        return fragments.get(arg0);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}