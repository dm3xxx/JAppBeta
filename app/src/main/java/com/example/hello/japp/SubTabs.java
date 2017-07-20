package com.example.hello.japp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class SubTabs extends Fragment {

    private String favor;

    public SubTabs() {}

    public SubTabs(String str) {
        this.favor = str;
    }


    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup container, Bundle savedInstanceState) {

        View v = inf.inflate(R.layout.fragment_sub_tabs, container, false);
        TextView txtV = v.findViewById(R.id.sub_tab_text);
        txtV.setText(this.favor);

        return v;
    }

}
