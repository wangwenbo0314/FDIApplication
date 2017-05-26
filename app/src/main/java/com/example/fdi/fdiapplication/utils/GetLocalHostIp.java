package com.example.fdi.fdiapplication.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 获取本地IP
 * @author moyi
 *
 */
public class GetLocalHostIp 
{
	public static String getLocalIPForJava(){
	    StringBuilder sb = new StringBuilder();
	    try {
	    	Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); 
	        while (en.hasMoreElements()) {
	            NetworkInterface intf = en.nextElement();
	            Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
	            while (enumIpAddr.hasMoreElements()) {
	                 InetAddress inetAddress = enumIpAddr.nextElement();
	                 if (!inetAddress.isLoopbackAddress()  && !inetAddress.isLinkLocalAddress() 
	                		 	&& inetAddress.isSiteLocalAddress()) {
	                	 sb.append(inetAddress.getHostAddress().toString()+"\n");
	                 }
	             }
	          }
	    } catch (SocketException e) {  }
	    return sb.toString();
	}
		
}
