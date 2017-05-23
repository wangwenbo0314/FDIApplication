package com.example.fdi.fdiapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fdi.fdiapplication.R;
import com.example.fdi.fdiapplication.bean.Contract;
import com.example.fdi.fdiapplication.view.HVListView;

import java.util.List;

/**
 * Created by Administrator on 2017/5/22 0022.
 * 数据处理adapter
 */

public class DataAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Contract> list;
    private HVListView mListView;

    public DataAdapter(LayoutInflater mInflater, HVListView mListView,List<Contract> list) {
        this.mInflater = mInflater;
        this.mListView = mListView;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();//固定显示50行数据
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item2, null);
        }
        ((TextView) convertView.findViewById(R.id.item1 )).setText(list.get(position).getContract());
        ((TextView) convertView.findViewById(R.id.item2 )).setText(list.get(position).getVariety());
        ((TextView) convertView.findViewById(R.id.item3)).setText(list.get(position).getNewPic());
        ((TextView) convertView.findViewById(R.id.item4 )).setText(list.get(position).getUpDown());
        ((TextView) convertView.findViewById(R.id.item5 )).setText(list.get(position).getChange());
        ((TextView) convertView.findViewById(R.id.item6 )).setText(list.get(position).getBuy());
        ((TextView) convertView.findViewById(R.id.item7 )).setText(list.get(position).getBuyQuantity());
        ((TextView) convertView.findViewById(R.id.item8 )).setText(list.get(position).getSell());
        ((TextView) convertView.findViewById(R.id.item9 )).setText(list.get(position).getSellQuantity());
        ((TextView) convertView.findViewById(R.id.item10 )).setText(list.get(position).getTurnover());
        ((TextView) convertView.findViewById(R.id.item11)).setText(list.get(position).getInventory());
        ((TextView) convertView.findViewById(R.id.item12)).setText(list.get(position).getTransaction());
        ((TextView) convertView.findViewById(R.id.item13)).setText(list.get(position).getYesterday());
        ((TextView) convertView.findViewById(R.id.item14)).setText(list.get(position).getNow());
        ((TextView) convertView.findViewById(R.id.item15)).setText(list.get(position).getMaxPic());

        //校正（处理同时上下和左右滚动出现错位情况）
        View child = ((ViewGroup) convertView).getChildAt(1);
        int head = mListView.getHeadScrollX();
        if (child.getScrollX() != head) {
            child.scrollTo(mListView.getHeadScrollX(), 0);
        }
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
