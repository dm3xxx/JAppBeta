package com.example.hello.japp;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TabTwo extends Fragment {

    private final String tabTag = "SUB TAB ";
    private final List<String> tabNames = new ArrayList<>(Arrays.asList("One", "Two", "Three"));
    private final String subTabTxt = "Favorite ";
    private final List<String> subStrings = new ArrayList<>(Arrays.asList("Food", "Animal", "Color"));

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup container, Bundle savedInstanceState) {

        View v = inf.inflate(R.layout.fragment_tab_two, container, false);

        ViewPager vp = v.findViewById(R.id.tab2_vp_container);
        CustomPagerAdapter pa = new CustomPagerAdapter(getChildFragmentManager());
        for (int i = 0; i < tabNames.size(); ++i)
            pa.addFragment(new SubTabs(subTabTxt + subStrings.get(i)), tabTag + tabNames.get(i));
        vp.setAdapter(pa);

        TabLayout tl = v.findViewById(R.id.tab_2_tabs);
        tl.setupWithViewPager(vp);

        return v;
    }
}
