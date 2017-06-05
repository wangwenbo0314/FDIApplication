package com.example.fdi.fdiapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fdi.fdiapplication.R;

import java.util.List;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class DetailsAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<String> list;

    public DetailsAdapter(LayoutInflater mInflater, List<String> list) {
        this.mInflater = mInflater;
        this.list = list;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.details_item, null);
        }
        ((TextView)convertView.findViewById(R.id.contract_detail)).setText(list.get(0));
        ((TextView)convertView.findViewById(R.id.orientation_detail)).setText(list.get(1));
        ((TextView)convertView.findViewById(R.id.kaiping_detail)).setText(list.get(2));
        ((TextView)convertView.findViewById(R.id.number_detail)).setText(list.get(3));
        ((TextView)convertView.findViewById(R.id.price_detail)).setText(list.get(4));
        ((TextView)convertView.findViewById(R.id.time_detail)).setText(list.get(5));
        return convertView;
    }
}
