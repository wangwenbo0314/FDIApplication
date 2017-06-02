package com.example.fdi.fdiapplication.Services;

import android.util.Log;

import com.example.fdi.fdiapplication.model.Heatbeatrsp;
import com.example.fdi.fdiapplication.model.Loginrsp;
import com.example.fdi.fdiapplication.myenum.EventType;
import com.example.fdi.fdiapplication.utils.MessageHelperFinal;
import com.example.fdi.fdiapplication.utils.NioClientHelper;
import com.example.fdi.fdiapplication.utils.ResponseEventArgs;
import com.example.fdi.fdiapplication.utils.ZIPUtilFinal;

/**
 * 事件监听 实现类
 *
 * @author Moyi
 */
public class EventListener implements SystemListener {

    public void handle(SystemEvent SystemEvent) {
        EventType type = SystemEvent.getEventType();
        switch (type) {
            case heartbeatrsp:
                    Heatbeatrsp.HeatbeatrspListener(SystemEvent);
                break;
            case loginrsp:
                Loginrsp.LoginrspListener(SystemEvent);
                break;
            case checkrsp:
                NioClientHelper.getNioClientHelperInstance().SendMainMessageASync(MessageHelperFinal.IGetMessage(SystemEvent.getResponseEventArgs().EXResponseBody));
                if (SystemEvent.getResponseEventArgs().Status == ResponseEventArgs.StatusCode.Success) {
                    Log.i("Tag06", "check 检查成功！");
                }
                break;
            case pushrsp:
                if(SystemEvent.getResponseEventArgs().Status==ResponseEventArgs.StatusCode.Success){
                    Log.i("Tag20","方法已执行");
                    try {
                        Log.i("Tag018", ZIPUtilFinal.decompressByString(SystemEvent.getResponseEventArgs().ResponseBody));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case allstockinforsp:
                if (SystemEvent.getResponseEventArgs().Status==ResponseEventArgs.StatusCode.Success){
                    try {
                        Log.i("Tag018", ZIPUtilFinal.decompressByString(SystemEvent.getResponseEventArgs().ResponseBody));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

}
