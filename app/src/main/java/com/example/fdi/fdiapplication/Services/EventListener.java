package com.example.fdi.fdiapplication.Services;

import android.util.Log;

import com.example.fdi.fdiapplication.model.Heatbeatrsp;
import com.example.fdi.fdiapplication.model.Loginrsp;
import com.example.fdi.fdiapplication.myenum.EventType;
import com.example.fdi.fdiapplication.utils.MessageHelperFinal;
import com.example.fdi.fdiapplication.utils.NioClientHelper;
import com.example.fdi.fdiapplication.utils.ResponseEventArgs;

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
            case onInMoneyrsp:
                NioClientHelper.getNioClientHelperInstance().SendMainMessageASync(MessageHelperFinal.IGetMessage(SystemEvent.getResponseEventArgs().EXResponseBody));
                if (SystemEvent.getResponseEventArgs().Status == ResponseEventArgs.StatusCode.Success) {
//				int result=userServiceImpl.mydao.updateDeposit(SystemEvent.getResponseEventArgs().ResponseBody);//依赖注入异常
//				if (result!=0)
//				{
//					Comet comet=new Comet();
//			        comet.setMsgCount("inMoneyrsp SUCCESS");
//			        new CometUtil().pushToALL(comet);
//				}else
//				{
//					Comet comet=new Comet();
//			        comet.setMsgCount("inMoneyrsp FAIL");
//			        new CometUtil().pushToALL(comet);
//				}

                } else {
//				Comet comet=new Comet();
//		        comet.setMsgCount("inMoneyrsp FAIL");
//		        new CometUtil().pushToALL(comet);
                }
                //收到消息回调

                break;
            case checkrsp:
                NioClientHelper.getNioClientHelperInstance().SendMainMessageASync(MessageHelperFinal.IGetMessage(SystemEvent.getResponseEventArgs().EXResponseBody));
                if (SystemEvent.getResponseEventArgs().Status == ResponseEventArgs.StatusCode.Success) {
                    Log.i("Tag06", "check 检查成功！");
                }
                break;
            case pushrsp:
                if(SystemEvent.getResponseEventArgs().Status==ResponseEventArgs.StatusCode.Success){
                    Log.i("Tag018", "udp 检查成功！");
                }
            default:
                break;
        }
    }

}
