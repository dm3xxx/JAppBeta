package com.example.hello.japp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Tab1Detail extends Fragment {

    private final String Name_Tag = "Name: ";
    private final String Code_Tag = "Code: ";
    private final String Color_Tag = "Color: ";
    private List<String> mInfo;

    public Tab1Detail() {
        mInfo = new ArrayList<>();

        mInfo.add(Name_Tag);
        mInfo.add(Code_Tag);
        mInfo.add(Color_Tag);
    }

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup vgc, Bundle savedInstanceState) {
        View v = inf.inflate(R.layout.fragment_tab1_detail, vgc, false);

        updateView(v);
        return v;
    }

    public void updateInfo(List<String> list) {
        mInfo = list;
        updateView(getView());
    }

    private void updateView(View v) {
        TextView tvName = v.findViewById(R.id.tab1_detail_name);
        TextView tvCode = v.findViewById(R.id.tab1_detail_code);
        TextView tvColor = v.findViewById(R.id.tab1_detail_color);

        tvName.setText(Name_Tag + mInfo.get(MainActivity.InfoOrder.Name.ordinal()));
        tvCode.setText(Code_Tag + mInfo.get(MainActivity.InfoOrder.Code.ordinal()));
        tvColor.setText(Color_Tag + mInfo.get(MainActivity.InfoOrder.Color.ordinal()));
    }
}
