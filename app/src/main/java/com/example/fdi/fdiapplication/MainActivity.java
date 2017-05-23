package com.example.fdi.fdiapplication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.fdi.fdiapplication.databinding.ActivityMainBinding;
import com.example.fdi.fdiapplication.databinding.ActivityMarketBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.shipanBut.setOnClickListener(this);
        binding.moniBut.setOnClickListener(this);
        binding.signBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MarketActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shipan_but:
                binding.shipanView.setVisibility(View.VISIBLE);
                binding.shipanBut.setTextColor(Color.parseColor("#3683D7"));
                binding.moniBut.setTextColor(Color.parseColor("#ffffff"));
                binding.moniView.setVisibility(View.INVISIBLE);
                binding.signBut.setText("登陆实盘");
                binding.mainLayout.setBackground(getResources().getDrawable(R.mipmap.shipan));
                break;
            case R.id.moni_but:
                binding.shipanView.setVisibility(View.INVISIBLE);
                binding.shipanBut.setTextColor(Color.parseColor("#ffffff"));
                binding.moniBut.setTextColor(Color.parseColor("#3683D7"));
                binding.moniView.setVisibility(View.VISIBLE);
                binding.signBut.setText("登陆模拟");
                binding.mainLayout.setBackground(getResources().getDrawable(R.mipmap.moni));
                break;
        }
    }
}
