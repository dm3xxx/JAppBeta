package com.example.hello.japp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private final String mColorTag = "Color: ";
    private final String mCodeTag = "Code: ";

    private final Context mContext;
    private  List<List<String>> mData;

    public CustomExpandableListAdapter(Context ctx, List<List<String>> data) {
        mContext = ctx;
        mData = data;
    }

    @Override
    public int getGroupCount() {
        return mData.size();
    }

    @Override
    public int getChildrenCount(int grpPos) {
        return 1;
    }

    @Override
    public Object getGroup(int grpPos) {
        return mData.get(grpPos);
    }

    @Override
    public Object getChild(int grpPos, int chPos) {
        return mData.get(grpPos).get(chPos);
    }

    @Override
    public long getGroupId(int grpPos) {
        return grpPos;
    }

    @Override
    public long getChildId(int grpPos, int chPos) {
        return chPos;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int grpPos, boolean isExpanded, View view, ViewGroup parent) {
        if (null == view) {
            LayoutInflater lif = (LayoutInflater) mContext.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = lif.inflate(R.layout.tab3_name_xlistview, null);
        }
//        final String name = (String) getGroup(grpPos);
        final String name = (String) getChild(grpPos, MainActivity.InfoOrder.Name.ordinal());
        TextView pplView = view.findViewById(R.id.tab3_name_textview);
        pplView.setText(name);

        return view;
    }

    @Override
    public View getChildView(int gp, int cp, boolean isLastChild, View view, ViewGroup parent) {

        if (null == view) {
            LayoutInflater inf = (LayoutInflater) mContext.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.tab3_detail_xlistview, null);
        }

        final String clrTxt = (String) getChild(gp, MainActivity.InfoOrder.Color.ordinal());
        final String codeTxt = (String) getChild(gp, MainActivity.InfoOrder.Code.ordinal());

        TextView clrView = view.findViewById(R.id.tab3_color_textview);
        TextView codeView = view.findViewById(R.id.tab3_code_textview);

        clrView.setText(mColorTag + clrTxt);
        codeView.setText(mCodeTag + codeTxt);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}