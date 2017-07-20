package com.example.hello.japp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public enum InfoOrder {Name, Code, Color}

    private enum Tabs {Tab_One, Tab_Two, Tab_Three}

    private final List<String> TabNames = new ArrayList<>();
    private final List<List<String>> mData = new ArrayList<List<String>>();
    private CustomViewPager mCVP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabNames.add(Tabs.Tab_One.toString());
        TabNames.add(Tabs.Tab_Two.toString());
        TabNames.add(Tabs.Tab_Three.toString());

        mCVP = (CustomViewPager) findViewById(R.id.main_tab_container);
        CustomPagerAdapter cpa = new CustomPagerAdapter(getSupportFragmentManager());
        cpa.addFragment(new TabOne(), TabNames.get(Tabs.Tab_One.ordinal()));
        cpa.addFragment(new TabTwo(), TabNames.get(Tabs.Tab_Two.ordinal()));
        cpa.addFragment(new TabThree(), TabNames.get(Tabs.Tab_Three.ordinal()));
        mCVP.setAdapter(cpa);
        mCVP.setSwipeable(false);
        mCVP.setScrollable(false);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.bottom_tabs);
        tabLayout.setupWithViewPager(mCVP);
    }

    @Override
    public void onBackPressed() {
        if (Tabs.Tab_One.ordinal() == mCVP.getCurrentItem()) {
            TabOne t1 = getTab1();
            if ((null != t1) && t1.handleBackPressed()) {
                return;
            }
        }

        super.onBackPressed();
    }

    private TabOne getTab1() {
        return (TabOne) getSupportFragmentManager()
                .findFragmentByTag("android:switcher:" + R.id.main_tab_container + ":0");
    }

    private Fragment getTab(int pos) {
        String tag = getResources().getString(R.string.fragment_switch);
        tag += R.id.main_tab_container + ":" + pos;
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    public void setColor(String s) {
        ((TabOne) getTab(Tabs.Tab_One.ordinal())).setColor(s);
    }

    public void updateListPage(String name, String code, String color) {
        mData.add(new ArrayList<>(Arrays.asList(name, code, color)));

        List<String> list = new ArrayList<>();
        for (List<String> ls : mData) {
            list.add(ls.get(InfoOrder.Name.ordinal()));
        }

        TabThree t3;
        if (null != (t3 = (TabThree) getTab(Tabs.Tab_Three.ordinal()))) {
            t3.update(mData);
        }
        getTab1().updateListTab(list);
    }

    public void updateDetailPage(int pos) {
        getTab1().updateDetailTab(mData.get(pos));
    }

    public List<List<String>> getLatestData() {
        return new ArrayList<>(mData);
    }
}