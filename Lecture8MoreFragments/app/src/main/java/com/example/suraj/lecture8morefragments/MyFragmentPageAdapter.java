package com.example.suraj.lecture8morefragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suraj on 6/22/2017.
 */

public class MyFragmentPageAdapter extends FragmentPagerAdapter {

    private int fragCount = 0;
    List<Fragment> fragmentList;

    public MyFragmentPageAdapter(FragmentManager fm,int count) {
        super(fm);
        this.fragCount = count;
        this.fragmentList = new ArrayList<>(count);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        try {
            fragment= fragmentList.get(position);
        }catch (IndexOutOfBoundsException e){
            fragment = BlankFragment.newInstance(
                    "Fragment :" + position, "Yet another Fragment"
            );
            fragmentList.add(position, fragment);
        }
            if(fragment==null) {
            fragment = BlankFragment.newInstance(
                    "Fragment :" + position, "Yet another Fragment"
            );
            fragmentList.set(position, fragment);
        }
        return fragment;
        }

    @Override
    public int getCount() {
        return fragCount;
    }
}
