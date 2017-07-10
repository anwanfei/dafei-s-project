package com.metashipanwf.androidsummarize.adapter;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.metashipanwf.androidsummarize.activity.AndriodOfBaseActivity;

import java.util.List;

/**
 * author:AnWanfei
 * time:2017/2/22.
 * Email:anwanfei_sp@163.com
 * function:
 */

public class MyExpandableListViewAdapter extends BaseExpandableListAdapter {

    AndriodOfBaseActivity andriodOfBaseActivity;
    List<List<String>> childArray;
    List<String> groupArray;

    public MyExpandableListViewAdapter(AndriodOfBaseActivity andriodOfBaseActivity, List<List<String>> childArray, List<String> groupArray) {
        this.andriodOfBaseActivity = andriodOfBaseActivity;
        this.childArray = childArray;
        this.groupArray = groupArray;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return childArray.get(groupPosition).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public int getChildrenCount(int groupPosition) {
        return childArray.get(groupPosition).size();
    }

    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        String string = childArray.get(groupPosition).get(childPosition);
        return getGenericView(string);
    }

    // group method stub
    public Object getGroup(int groupPosition) {
        return groupArray.get(groupPosition);
    }

    public int getGroupCount() {
        return groupArray.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String string = groupArray.get(groupPosition);
        return getGenericView(string);
    }

    // View stub to create Group/Children 's View
    public TextView getGenericView(String string) {
        // Layout parameters for the ExpandableListView
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, 64);
        TextView text = new TextView(andriodOfBaseActivity);
        text.setLayoutParams(layoutParams);
        // Center the text vertically
        text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        // Set the text starting position
        text.setPadding(36, 0, 0, 0);
        text.setText(string);
        return text;
    }

    public boolean hasStableIds() {
        return false;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}