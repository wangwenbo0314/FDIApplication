package com.example.fdi.fdiapplication;

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
import com.example.fdi.fdiapplication.adapter.DataAdapter;
import com.example.fdi.fdiapplication.bean.Contract;
import com.example.fdi.fdiapplication.databinding.ActivityMarketBinding;
import com.example.fdi.fdiapplication.view.HVListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 合约的主要信息显示
 */
public class MarketActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMarketBinding binding;
    private LayoutInflater mInflater;
    private HVListView mListView;
    List<Contract> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_market);
        initView();//初始化数据
        mInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        setBar();//设置ToolBar
        setCheck();//设置市场选择
        mListView=binding.marketList;
        //设置列头
        mListView.mListHead = (LinearLayout) findViewById(R.id.head);
        //设置数据
        mListView.setAdapter(new DataAdapter(mInflater,mListView,list));
    }

    private void initView() {
        Contract contract = new Contract("RB1710","螺丝","175","50","76","34","43","343","343","112","212","23321","3232","232","223");
        list=new ArrayList<>();
        list.add(contract);
    }

    /*
    设置市场选择器监听事件
     */
    private void setCheck() {
        binding.zixuanMarket.setOnClickListener(this);
        binding.hangkongMarket.setOnClickListener(this);
        binding.usaMarket.setOnClickListener(this);
        binding.marketList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MarketActivity.this,DetailsActivity.class);
                startActivity(intent);
            }
        });
    }
    /*
    设置Toolbar
     */
    private void setBar() {
        binding.marketBar.setTitle(" ");
        setSupportActionBar(binding.marketBar);
        ActionBar bar = getSupportActionBar();
        if (bar!=null){
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
        显示侧滑菜单
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                binding.drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }
    /*
    设置市场选择指示器点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hangkong_market:
                binding.zixuanMarket.setTextColor(Color.parseColor("#B7B7B7"));
                binding.zixuanView.setVisibility(View.INVISIBLE);
                binding.usaMarket.setTextColor(Color.parseColor("#B7B7B7"));
                binding.usaView.setVisibility(View.INVISIBLE);
                binding.hangkongMarket.setTextColor(Color.parseColor("#FC7506"));
                binding.hangkongView.setVisibility(View.VISIBLE);
                break;
            case R.id.usa_market:
                binding.zixuanMarket.setTextColor(Color.parseColor("#B7B7B7"));
                binding.zixuanView.setVisibility(View.INVISIBLE);
                binding.hangkongMarket.setTextColor(Color.parseColor("#B7B7B7"));
                binding.hangkongView.setVisibility(View.INVISIBLE);
                binding.usaMarket.setTextColor(Color.parseColor("#FC7506"));
                binding.usaView.setVisibility(View.VISIBLE);
                break;
            case R.id.zixuan_market:
                binding.usaMarket.setTextColor(Color.parseColor("#B7B7B7"));
                binding.usaView.setVisibility(View.INVISIBLE);
                binding.hangkongMarket.setTextColor(Color.parseColor("#B7B7B7"));
                binding.hangkongView.setVisibility(View.INVISIBLE);
                binding.zixuanMarket.setTextColor(Color.parseColor("#FC7506"));
                binding.zixuanView.setVisibility(View.VISIBLE);
                break;
            case R.id.market_bar:
                break;
        }
    }
}
