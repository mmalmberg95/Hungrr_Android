package com.drake.android.cs188project3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Matthew on 11/30/2017.
 */

public class foodAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private int i;

    @Override
    public int getCount() {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return view;
    }
    }

