package com.example.fdi.fdiapplication.model;

import android.util.Log;

import com.example.fdi.fdiapplication.Services.SystemEvent;
import com.example.fdi.fdiapplication.utils.MessageHelperFinal;
import com.example.fdi.fdiapplication.utils.NioClientHelper;
import com.example.fdi.fdiapplication.utils.ResponseEventArgs;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

public class Heatbeatrsp {

    public static void HeatbeatrspListener(SystemEvent systemEvent) {
        if (systemEvent.getResponseEventArgs().Status == ResponseEventArgs.StatusCode.Success) {
            NioClientHelper.getNioClientHelperInstance().SendMainMessageASync(MessageHelperFinal.IGetMessage(systemEvent.getResponseEventArgs().EXResponseBody));
            Log.i("Tag03", systemEvent.getResponseEventArgs().EXResponseBody);
        }
    }
}
