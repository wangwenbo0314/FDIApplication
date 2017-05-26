package com.example.fdi.fdiapplication;

import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.fdi.fdiapplication.databinding.ActivityDetailsBinding;
import com.example.fdi.fdiapplication.utils.UDPClientHelper;

/**
 * 单个合约详情页面
 */

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityDetailsBinding binding;
    UDPClientHelper udpClientHelper;
    Thread thread;
    private Dialog dialog;
    private View inflate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_details);
        initView();
        setTabButLister();//设置底部5个按钮的监听
    }

    private void initView() {
        thread=new Thread(new Runnable() {
            @Override
            public void run() {
                udpClientHelper= UDPClientHelper.getUDPClientHelperInstance();
            }
        });
        thread.start();
    }

    private void setTabButLister() {
        binding.knockdownImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.knockdown_image:
                Intent intent = new Intent(this,TransactionListActivity.class);
                startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        udpClientHelper.Close();
        super.onDestroy();
    }
}
