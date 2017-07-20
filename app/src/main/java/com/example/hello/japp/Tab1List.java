package com.example.hello.japp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class Tab1List extends Fragment implements AdapterView.OnItemClickListener {

    private final List<String> mList = new ArrayList<>();
    private ListView mLV;

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup vgc, Bundle savedInstanceState) {

        View v = inf.inflate(R.layout.fragment_tab1_list, vgc, false);

        mLV = v.findViewById(R.id.tab1_list_listview);
        mLV.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.text_view_center, mList));
        mLV.setOnItemClickListener(this);

        return  v;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int spot, long id) {
        ((MainActivity) getActivity()).updateDetailPage(spot);
    }

    public void updateList(List<String> data) {
        mList.clear();
        mList.addAll(data);
        ((BaseAdapter) mLV.getAdapter()).notifyDataSetChanged();
    }
}
