package com.example.hello.japp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class TabOne extends Fragment {

    private enum Pages {Input, List, Detail}
    private final List<String> PageNames;
    private final List<Fragment> mPages;
    private int curFragment;
    Tab1Picker mPicker;

    public TabOne() {
        PageNames = new ArrayList<>();
        mPages = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PageNames.add(Pages.Input.toString());
        PageNames.add(Pages.List.toString());
        PageNames.add(Pages.Detail.toString());

        curFragment = Pages.Input.ordinal();

        mPages.add(new Tab1Input());
        mPages.add(new Tab1List());
        mPages.add(new Tab1Detail());

        mPicker = new Tab1Picker();
    }

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup vg, Bundle savedInstanceState) {

        View v = inf.inflate(R.layout.fragment_tab_one, vg, false);

        CustomViewPager cvp = v.findViewById(R.id.tab_1_cvp_container);
        CustomPagerAdapter cpa = new CustomPagerAdapter(getChildFragmentManager());
        cpa.addFragment(mPages.get(Pages.Input.ordinal()), PageNames.get(Pages.Input.ordinal()));
        cpa.addFragment(mPages.get(Pages.List.ordinal()), PageNames.get(Pages.List.ordinal()));
        cpa.addFragment(mPages.get(Pages.Detail.ordinal()), PageNames.get(Pages.Detail.ordinal()));

        cvp.setAdapter(cpa);
        cvp.setSwipeable(false);
        cvp.setScrollable(false);
        cvp.setCurrentItem(curFragment);

        return v;
    }

    public boolean handleBackPressed() {
        if (Pages.Input.ordinal() == curFragment) {
            return false;
        }

        ((CustomViewPager) getView().findViewById(R.id.tab_1_cvp_container))
                .setCurrentItem(--curFragment);
        return true;
    }

    public void showPage(Pages p) {
        curFragment = p.ordinal();
        ((CustomViewPager) getView().findViewById(R.id.tab_1_cvp_container))
                .setCurrentItem(curFragment);
    }

    public void showPicker() {
        mPicker.show(getChildFragmentManager(), getResources().getString(R.string.tab_1_picker_str));
    }

    public void updateListTab(List<String> data) {
        Tab1List tabList = (Tab1List) getChildFragmentManager()
                .findFragmentByTag("android:switcher:" + R.id.tab_1_cvp_container + ":" + Pages.List.ordinal());
        tabList.updateList(data);
        showPage(Pages.List);
    }

    public void updateDetailTab(List<String> list) {
        Tab1Detail tabDetail = (Tab1Detail) getChildFragmentManager()
                .findFragmentByTag("android:switcher:" + R.id.tab_1_cvp_container + ":" + Pages.Detail.ordinal());
        tabDetail.updateInfo(list);
        showPage(Pages.Detail);
    }

    public void setColor(String color) {
        Tab1Input inputTab = (Tab1Input) getChildFragmentManager()
                .findFragmentByTag("android:switcher:" + R.id.tab_1_cvp_container + ":" + Pages.Input.ordinal());
        inputTab.setColor(color);
    }

//    private Fragment getTab(int pos) {
//        String tag = getResources().getString(R.string.fragment_switch);
//        tag += R.id.tab_1_cvp_container + ":" + pos;
//        return getChildFragmentManager().findFragmentByTag(tag);
////        return getChildFragmentManager()
////                .findFragmentByTag(R.string.fragment_switch + R.id.tab_1_cvp_container + ":" + pos);
//    }
}