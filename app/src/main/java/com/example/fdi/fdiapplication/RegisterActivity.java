package com.example.fdi.fdiapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fdi.fdiapplication.databinding.ActivityDetailsBinding;
import com.example.fdi.fdiapplication.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityRegisterBinding binding;//创建布局Bingding对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_register);//binding布局
        initView();//初始化控件,并绑定监听器
    }
    /*
     *初始化控件，并绑定监听器
     */
    private void initView() {
        binding.regMoniBut.setOnClickListener(this);
        binding.regShipanBut.setOnClickListener(this);
        binding.regNextBut.setOnClickListener(this);
    }
    /*
     *对于按钮进行监听，并执行相应流程
     */
    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reg_shipan_but:
                binding.regShipanView.setVisibility(View.VISIBLE);
                binding.regShipanBut.setTextColor(Color.parseColor("#3683D7"));
                binding.regMoniBut.setTextColor(Color.parseColor("#ffffff"));
                binding.regMoniView.setVisibility(View.INVISIBLE);
                break;
            case R.id.reg_moni_but:
                binding.regShipanView.setVisibility(View.INVISIBLE);
                binding.regShipanBut.setTextColor(Color.parseColor("#ffffff"));
                binding.regMoniBut.setTextColor(Color.parseColor("#3683D7"));
                binding.regMoniView.setVisibility(View.VISIBLE);
                break;
            case R.id.reg_next_but:
                Intent intent = new Intent(this,Register2Activity.class);
                startActivity(intent);
                break;
        }
    }
}
