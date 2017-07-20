package com.example.hello.japp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import java.util.ArrayList;
import java.util.List;


public class TabThree extends Fragment implements
        OnChildClickListener, OnGroupClickListener, OnGroupCollapseListener, OnGroupExpandListener {

    private ExpandableListView mXpbListView;
    private CustomExpandableListAdapter mCxpListAdptr;
    private List<List<String>> mListData;
    private int curIdx;

    public TabThree() {
        mListData = new ArrayList<List<String>>();
        curIdx = -1;

        System.out.println("TabThree");
    }

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup vgc, Bundle savedInstanceState) {
        View v = inf.inflate(R.layout.fragment_tab_three, vgc, false);

        mListData = ((MainActivity) getActivity()).getLatestData();
        mCxpListAdptr = new CustomExpandableListAdapter(getActivity(), mListData);
        mXpbListView = v.findViewById(R.id.tab3_xb_list_view);
        mXpbListView.setAdapter(mCxpListAdptr);

        mXpbListView.setOnGroupClickListener(this);
        mXpbListView.setOnGroupExpandListener(this);
        mXpbListView.setOnGroupCollapseListener(this);
        mXpbListView.setOnChildClickListener(this);

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int gPos, long id) {
        return false;
    }

    @Override
    public void onGroupExpand(int idx) {
        curIdx = idx;
        int sz = mCxpListAdptr.getGroupCount();
        for (int i = 0; i < sz; ++i) {
            if (i != curIdx) {
                mXpbListView.collapseGroup(i);
            }
        }
    }

    @Override
    public void onGroupCollapse(int i) {
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int pIdx, int cIdx, long id) {
        return false;
    }

    public void update(List<List<String>> data) {
        mListData.clear();
        mListData.addAll(data);
        ((BaseAdapter) mXpbListView.getAdapter()).notifyDataSetChanged();
    }
}
