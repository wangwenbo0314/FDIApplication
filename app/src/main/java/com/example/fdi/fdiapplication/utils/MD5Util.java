package com.example.fdi.fdiapplication.utils;

import java.security.MessageDigest;

public class MD5Util {
	public static String md5Encode(String str) 
	{ 
		StringBuffer buf = new StringBuffer(); 
	  try {
		    MessageDigest md5 = MessageDigest.getInstance("MD5");
		    md5.update(str.getBytes()); 
		    byte bytes[] = md5.digest(); 
		    for(int i = 0; i < bytes.length; i++) 
		    { 
		    	String s = Integer.toHexString(bytes[i] & 0xff); 
		        if(s.length()==1){ buf.append("0"); }
		        buf.append(s); }
		    }
	    catch(Exception ex) 
	  {
	    	
	  } 
	  return buf.toString(); 
	}
}
