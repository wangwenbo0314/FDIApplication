package com.example.fdi.fdiapplication;
/**
 * 文件名：MarketActivity
 * 描    述：用户的登陆界面，包含实盘和模拟
 * 作    者：
 * 时    间：
 * 版    权：
 */
import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.fdi.fdiapplication.Services.SystemListener;
import com.example.fdi.fdiapplication.databinding.ActivityMainBinding;
import com.example.fdi.fdiapplication.model.Loginrsp;
import com.example.fdi.fdiapplication.utils.MessageHelperFinal;
import com.example.fdi.fdiapplication.utils.NioClientHelper;
import com.example.fdi.fdiapplication.utils.UDPClientHelper;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityMainBinding binding;//布局banding对象；
    NioClientHelper ncHelper;
    static Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);//初始化界面
        netClint();//连接网络
        binding.shipanBut.setOnClickListener(this);//实盘标签监听器
        binding.moniBut.setOnClickListener(this);//模拟监听器
        binding.signBut.setOnClickListener(this);//登陆监听器
    }

    /*
     * 方法名：netClint
     * 功  能：新建异步对象，建立网络连接
     * 参  数：无
     * 返回值：无
    */
    @SuppressLint("WrongConstant")
    private void netClint() {
        thread=new Thread(new Runnable() {
            @Override
            public void run() {
                ncHelper = NioClientHelper.getNioClientHelperInstance();
            }
        });
        thread.start();
    }

    /*
    * 方法名：onClick
    * 功  能：按钮点击事件
    * 参  数：View v - 按钮的view
    * 返回值：无
    */
    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击实盘标签
            case R.id.shipan_but:
                binding.shipanView.setVisibility(View.VISIBLE);
                binding.shipanBut.setTextColor(Color.parseColor("#3683D7"));
                binding.moniBut.setTextColor(Color.parseColor("#ffffff"));
                binding.moniView.setVisibility(View.INVISIBLE);
                binding.signBut.setText("登陆实盘");
                binding.mainLayout.setBackground(getResources().getDrawable(R.mipmap.shipan));
                break;
            //点击模拟标签
            case R.id.moni_but:
                binding.shipanView.setVisibility(View.INVISIBLE);
                binding.shipanBut.setTextColor(Color.parseColor("#ffffff"));
                binding.moniBut.setTextColor(Color.parseColor("#3683D7"));
                binding.moniView.setVisibility(View.VISIBLE);
                binding.signBut.setText("登陆模拟");
                binding.mainLayout.setBackground(getResources().getDrawable(R.mipmap.moni));
                break;
            //点击登陆标签，跳转到对应的实盘或模拟界面
            case R.id.sign_but:
                ncHelper.SendMainMessageASync(MessageHelperFinal.LoginMessage("029005076","123456"));
                new Loginrsp(this);
        }
    }
    @Override
    protected void onDestroy() {
        ncHelper.Close();
        super.onDestroy();
    }
}
