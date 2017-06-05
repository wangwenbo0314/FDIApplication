package com.example.fdi.fdiapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
        binding.firmRg.setOnClickListener(this);
        binding.simuRg.setOnClickListener(this);
        binding.nextRg.setOnClickListener(this);
    }
    /*
     *对于按钮进行监听，并执行相应流程
     */
    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.firm_rg:
                binding.firmRg.setBackground(getResources().getDrawable(R.mipmap.ic_bg_but));
                binding.simuRg.setBackground(null);
                break;
            case R.id.simu_rg:
                binding.simuRg.setBackground(getResources().getDrawable(R.mipmap.ic_bg_but));
                binding.firmRg.setBackground(null);
                break;
            case R.id.next_rg:
                Intent intent = new Intent(this,Register2Activity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
