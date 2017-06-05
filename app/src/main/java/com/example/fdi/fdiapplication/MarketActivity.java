package com.example.fdi.fdiapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.example.fdi.fdiapplication.adapter.MarketDataAdapter;
import com.example.fdi.fdiapplication.bean.Contract;
import com.example.fdi.fdiapplication.databinding.ActivityMarketBinding;
import com.example.fdi.fdiapplication.view.HVListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名：MarketActivity
 * 描  述：对用户进行合约情况的详细介绍
 * 作  者：
 * 时  间：
 * 版  权：
 */
public class MarketActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMarketBinding binding;//获取布局binding对象
    private LayoutInflater mInflater;//获取inflater对象
    private HVListView mListView;//获取listview对象
    List<Contract> list;//获取数据集合
    MarketDataAdapter dataAdapter;//创建adapter对象
    Contract contract;
    Contract contract2;
    Contract contract3;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_market);
        initView();//初始化数据
        mInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        setBar();//设置ToolBar
        setCheck();//设置市场选择
        setListView();//对listview进行数据填充
    }
    private void setListView() {
        mListView = binding.marketList;//初始化listview
        mListView.mListHead = (LinearLayout) findViewById(R.id.head);//设置列头
        dataAdapter = new MarketDataAdapter(mInflater, mListView, list);//设置数据
        mListView.setAdapter(dataAdapter);//进行关联
    }

    /*
     * 初始化数据
     */
    @SuppressLint("WrongConstant")
    private void initView() {
        contract = new Contract("RB1710", "螺丝", "175", "50", "76", "34", "43", "343", "343", "112", "212", "23321", "3232", "232", "223");
        contract2 = new Contract("RB1711", "钢材", "175", "50", "76", "34", "43", "343", "343", "112", "212", "23321", "3232", "232", "223");
        contract3 = new Contract("RB1712", "黄金", "175", "50", "76", "34", "43", "343", "343", "112", "212", "23321", "3232", "232", "223");
        list = new ArrayList<>();//创建集合
        list.add(contract);//加入Contract对象
    }

    /*
     *设置按钮监听器
     */
    private void setCheck() {
        binding.zixuanMarket.setOnClickListener(this);
        binding.hangkongMarket.setOnClickListener(this);
        binding.usaMarket.setOnClickListener(this);
        binding.marketList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MarketActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    /*
     * 设置Toolbar
     */
    private void setBar() {
        binding.marketBar.setTitle(" ");
        setSupportActionBar(binding.marketBar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                binding.drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    /*
     * 显示侧滑菜单，并设置点击监听事件
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                binding.drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

    /*
     * 顶部导航按钮监听，并执行相应流程
     */
    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hangkong_market:
                binding.zixuanMarket.setTextColor(Color.parseColor("#B7B7B7"));
                binding.zixuanView.setVisibility(View.INVISIBLE);
                binding.usaMarket.setTextColor(Color.parseColor("#B7B7B7"));
                binding.usaView.setVisibility(View.INVISIBLE);
                binding.hangkongMarket.setTextColor(Color.parseColor("#FC7506"));
                binding.hangkongView.setVisibility(View.VISIBLE);
                list.clear();
                list.add(contract2);
                dataAdapter.notifyDataSetChanged();
                break;
            case R.id.usa_market:
                binding.zixuanMarket.setTextColor(Color.parseColor("#B7B7B7"));
                binding.zixuanView.setVisibility(View.INVISIBLE);
                binding.hangkongMarket.setTextColor(Color.parseColor("#B7B7B7"));
                binding.hangkongView.setVisibility(View.INVISIBLE);
                binding.usaMarket.setTextColor(Color.parseColor("#FC7506"));
                binding.usaView.setVisibility(View.VISIBLE);
                list.clear();
                list.add(contract3);
                dataAdapter.notifyDataSetChanged();
                break;
            case R.id.zixuan_market:
                binding.usaMarket.setTextColor(Color.parseColor("#B7B7B7"));
                binding.usaView.setVisibility(View.INVISIBLE);
                binding.hangkongMarket.setTextColor(Color.parseColor("#B7B7B7"));
                binding.hangkongView.setVisibility(View.INVISIBLE);
                binding.zixuanMarket.setTextColor(Color.parseColor("#FC7506"));
                binding.zixuanView.setVisibility(View.VISIBLE);
                list.clear();
                list.add(contract);
                dataAdapter.notifyDataSetChanged();
                break;
            case R.id.market_bar:
                break;
        }
    }
}
