package com.box8.box8home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * MenuPageAdapter Class
 */
public class MenuPageAdapter extends FragmentPagerAdapter {

    // List Of Fragment
    private List<Fragment> fragmentList;

    /**
     * Constructor
     *
     * @param manager manage
     */
    public MenuPageAdapter(FragmentManager manager, List<Fragment> fragments) {
        super(manager);
        this.fragmentList = fragments;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position position
     */
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
