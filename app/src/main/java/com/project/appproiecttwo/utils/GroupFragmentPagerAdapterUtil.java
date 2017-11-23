package com.project.appproiecttwo.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lenovo on 2017/10/11.
 */

public class GroupFragmentPagerAdapterUtil extends FragmentPagerAdapter {
    private final String TAG = "GroupFragmentPager";
    private List<Fragment> list_fragment;//fragment列表
    private List<String> list_String;//Tab的名字

    public GroupFragmentPagerAdapterUtil(FragmentManager fm, List<Fragment> fragmentList, List<String> stringList) {
        super(fm);
        list_fragment = fragmentList;
        list_String = stringList;
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_fragment.size();
    }

    public CharSequence getPageTitle(int position) {
        return list_String.get(position % list_String.size());
    }
}
