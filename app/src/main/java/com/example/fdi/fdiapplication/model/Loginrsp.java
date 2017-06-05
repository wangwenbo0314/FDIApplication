package com.example.fdi.fdiapplication.model;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.fdi.fdiapplication.MarketActivity;
import com.example.fdi.fdiapplication.Services.SystemEvent;
import com.example.fdi.fdiapplication.utils.MessageHelperFinal;
import com.example.fdi.fdiapplication.utils.NioClientHelper;
import com.example.fdi.fdiapplication.utils.ResponseEventArgs;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

public class Loginrsp {
    private static Context context;

    public Loginrsp(Context context) {
        this.context=context;
    }

    public static void LoginrspListener(SystemEvent systemEvent){
        NioClientHelper.getNioClientHelperInstance().SendMainMessageASync(MessageHelperFinal.IGetMessage(systemEvent.getResponseEventArgs().EXResponseBody));
        if (systemEvent.getResponseEventArgs().Status == ResponseEventArgs.StatusCode.Success) {
            Intent intent = new Intent(context, MarketActivity.class);
            context.startActivity(intent);
        } else {
            Toast.makeText(context,"账号或密码错误，请重新输入",Toast.LENGTH_SHORT).show();
        }
    }
}
