package com.example.fdi.fdiapplication.Services;

import com.example.fdi.fdiapplication.myenum.EventType;
import com.example.fdi.fdiapplication.utils.ResponseEventArgs;

/**
 * 系统事件类
 * @author Moyi
 *
 */
public interface SystemEvent 
{
	public EventType getEventType();
	public ResponseEventArgs getResponseEventArgs();
	public void addDemoListener(SystemListener dl) ;
	public void notifyDemoEvent() ;
}
