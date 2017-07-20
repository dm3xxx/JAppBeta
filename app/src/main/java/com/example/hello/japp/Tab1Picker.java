package com.example.hello.japp;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;


public class Tab1Picker extends DialogFragment
        implements AdapterView.OnItemClickListener, Toolbar.OnMenuItemClickListener {

    private String mDeColor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        mDeColor = getResources().getString(R.string.default_color_str);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inf,ViewGroup vgc, Bundle savedInstanceState) {
        View v = inf.inflate(R.layout.fragment_tab1_picker, vgc, false);

        Toolbar toolbar = v.findViewById(R.id.tab1_picker_toolbar);
        if (toolbar != null) {
            toolbar.inflateMenu(R.menu.tab1_picker_menu);
        }
        toolbar.setOnMenuItemClickListener(this);

        List<String> colors = Arrays.asList(getResources().getStringArray(R.array.colors));
        ListView lv = v.findViewById(R.id.tab1_picker_listview);
        ArrayAdapter<String> ad = new ArrayAdapter<>(getActivity(), R.layout.text_view_center, colors);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(this);

        return v;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.tab1_picker_done) {
            ((MainActivity) getActivity()).setColor(mDeColor);
            dismiss();
            return true;
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
        mDeColor = adapterView.getAdapter().getItem(pos).toString();
    }
}
