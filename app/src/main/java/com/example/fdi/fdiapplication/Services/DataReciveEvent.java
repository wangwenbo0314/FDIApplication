package com.example.fdi.fdiapplication.Services;

import java.util.Enumeration;
import java.util.Vector;

import com.example.fdi.fdiapplication.myenum.EventType;
import com.example.fdi.fdiapplication.utils.ResponseEventArgs;

public class DataReciveEvent implements SystemEvent {

    public Vector<SystemListener> repository = new Vector();//监听自己的监听器队列

    EventType type;
    ResponseEventArgs e;

    /**
     * 判断监听事件
     *
     * @param ee 返回事件的内容
     */
    public DataReciveEvent(ResponseEventArgs ee) {
        this.e = ee;
        if (e.CommandName.equalsIgnoreCase("heartbeatrsp")) {
            type = EventType.heartbeatrsp;
        } else if (e.CommandName.equalsIgnoreCase("loginrsp")) {
            type = EventType.loginrsp;
        } else if (e.CommandName.equalsIgnoreCase("onInMoneyrsp")) {
            type = EventType.onInMoneyrsp;
        } else if (e.CommandName.equalsIgnoreCase("checkrsp")) {
            type = EventType.checkrsp;
        } else if (e.CommandName.equalsIgnoreCase("pushrsp")){
            type =EventType.pushrsp;
        }else {
            type = null;
        }
    }

    public EventType getEventType() {
        return this.type;
    }


    public ResponseEventArgs getResponseEventArgs() {
        return e;
    }


    /**
     * 多监听放入队列
     *
     * @param dl
     */
    public void addDemoListener(SystemListener dl) {
        repository.add(dl);
    }


    /**
     * 多监听执行
     */
    public void notifyDemoEvent() {//通知所有的监听器
        Enumeration<SystemListener> enumn = repository.elements();
        while (enumn.hasMoreElements()) {
            SystemListener dl = enumn.nextElement();
            dl.handle(new DataReciveEvent(this.e));
        }
    }

}
