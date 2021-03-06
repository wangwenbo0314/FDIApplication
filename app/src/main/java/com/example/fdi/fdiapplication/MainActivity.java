package com.example.fdi.fdiapplication;
/**
 * 文件名： MainActivity
 * 描    述：用户的登陆界面，包含实盘和模拟
 * 作    者：
 * 时    间：
 * 版    权：
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.fdi.fdiapplication.databinding.ActivityMainBinding;
import com.example.fdi.fdiapplication.utils.NioClientHelper;
import com.example.fdi.fdiapplication.utils.UDPClientHelper;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;//布局banding对象；
    NioClientHelper ncHelper;//初始化TCP网络连接
    UDPClientHelper udpClientHelper;//初始化UDP网络连接
    static Thread thread;//初始化网络

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);//初始化界面
        netClint();//连接网络
        binding.firm.setOnClickListener(this);//实盘标签监听器
        binding.simu.setOnClickListener(this);//模拟监听器
        binding.actionLogin.setOnClickListener(this);//登陆监听器
        binding.register.setOnClickListener(this);//注册监听器
    }

    /*
     * 新建异步对象，建立网络连接
     */
    @SuppressLint("WrongConstant")
    private void netClint() {
        thread = new Thread(() -> {
            ncHelper = NioClientHelper.getNioClientHelperInstance();
            udpClientHelper=UDPClientHelper.getUDPClientHelperInstance();
            isFristLogin();//第一次登陆需要对合约进行镜像
        });
        thread.start();
    }
    /*
     *判断是否为第一次登陆
     */
    private void isFristLogin() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int currentVersion = info.versionCode;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int lastVersion = prefs.getInt("VERSION_KEY", 0);
        if (currentVersion > lastVersion) {
            //如果当前版本大于上次版本，该版本属于第一次启动
            udpClientHelper.SendMainMessageASync("FirstLogin ");
            //将当前版本写入preference中，则下次启动的时候，据此判断，不再为首次启动
            prefs.edit().putInt("VERSION_KEY",currentVersion).commit();
        }
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
            case R.id.firm:
                binding.actionLogin.setText("登    陆    实    盘");
                binding.firm.setBackground(getResources().getDrawable(R.mipmap.ic_bg_but));
                binding.simu.setBackground(null);
                break;
            //点击模拟标签
            case R.id.simu:
                binding.actionLogin.setText("登    陆    模    拟");
                binding.simu.setBackground(getResources().getDrawable(R.mipmap.ic_bg_but));
                binding.firm.setBackground(null);
                break;
            //点击登陆标签，跳转到对应的实盘或模拟界面
            case R.id.action_login:
//                String userName=binding.username.getText().toString();
//                String userPassword=binding.password.getText().toString();
////                ncHelper.SendMainMessageASync(MessageHelperFinal.LoginMessage(userName,userPassword));
//                ncHelper.SendMainMessageASync(MessageHelperFinal.LoginMessage("029005076","123456"));
//                new Loginrsp(this);
                Intent intent2 = new Intent(this ,MarketActivity.class);
                startActivity(intent2);
                break;
            //跳转注册页面
            case R.id.register:
                Intent intent = new Intent(this ,RegisterActivity.class);
                startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        ncHelper.Close();
        udpClientHelper.Close();
        super.onDestroy();
    }
}
