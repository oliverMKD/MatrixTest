package com.oli.matrixtest.helpers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

public class VPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragmenti = new ArrayList<Fragment>();


    public void dodadiFragment (Fragment fragment){
        fragmenti.add(fragment);

    }

    public VPagerAdapter(FragmentManager fm) {
        super(fm);

    }
    @Override
    public int getCount() {
        return fragmenti.size();
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
    @Override
    public Fragment getItem(int position) {
        return fragmenti.get(position);
    }

}
