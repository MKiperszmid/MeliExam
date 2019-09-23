package com.example.meliinterview.View.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class PictureAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;

    public PictureAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragmentList.get(position);
    }

    @Override
    public int getCount() {
        if (this.fragmentList == null)
            return -1;
        return this.fragmentList.size();
    }
}
