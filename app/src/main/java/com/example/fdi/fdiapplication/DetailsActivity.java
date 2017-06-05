package com.example.fdi.fdiapplication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.fdi.fdiapplication.databinding.ActivityDetailsBinding;
import com.example.fdi.fdiapplication.utils.PopViewUtil;

/**
 * 单个合约详情页面
 */

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityDetailsBinding binding;
    PopViewUtil popViewUtils;
    View menuView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_details);
        initView();
        setTabButLister();//设置底部5个按钮的监听
    }

    private void initView() {
        menuView= LayoutInflater.from(this).inflate(R.layout.activity_transaction_list, null);
        popViewUtils = new PopViewUtil(R.layout.activity_transaction_list,this);
    }

    private void setTabButLister() {
        binding.knockdownImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.knockdown_image:
                popViewUtils.initPopView(menuView);
                //点击某控件控制显示与隐藏
                popViewUtils.changePopupWindowState(binding.knockdownImage);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
