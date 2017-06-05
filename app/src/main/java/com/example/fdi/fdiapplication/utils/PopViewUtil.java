package com.example.fdi.fdiapplication.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.fdi.fdiapplication.R;
import com.example.fdi.fdiapplication.adapter.DetailsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class PopViewUtil {

    private int layout;
    private Context context;
    private PopupWindow pop;
    private List<String> list;
    private DetailsAdapter detailsAdapter;
    private LayoutInflater mInflater;//获取inflater对象

    public PopViewUtil(int layout, Context context) {
        this.layout = layout;
        this.context = context;
    }

    public void initPopView(View menuView) {

        list = new ArrayList<>();
        list.add("snd1234");
        list.add("dss");
        list.add("212");
        list.add("21");
        list.add("qeqwe");
        list.add("1212");
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = mInflater.inflate(layout, null);
        detailsAdapter = new DetailsAdapter(mInflater, list);
        ListView listView = (ListView) contentView.findViewById(R.id.listview_tran);
        contentView.findViewById(R.id.textview_tran).setVisibility(View.GONE);
        listView.setAdapter(detailsAdapter);
        pop = new PopupWindow(menuView, LinearLayout.LayoutParams.MATCH_PARENT, 700, true);
        pop.setAnimationStyle(R.style.MenuAnimationFade);
        pop.setFocusable(true);

    }

    /**
     * PopupWindow 的显示和隐藏
     */
    public void changePopupWindowState(View view) {
        if (pop.isShowing()) {
            pop.dismiss();
        } else {
            pop.showAtLocation(view, Gravity.BOTTOM, 0, 210);
        }
    }
}

