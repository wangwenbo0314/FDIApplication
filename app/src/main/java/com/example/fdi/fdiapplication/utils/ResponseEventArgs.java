package com.example.fdi.fdiapplication.utils;
import java.util.Date;

/**
 * 解析服务器协议
 * @author moyi
 *
 */
public class ResponseEventArgs
{
	 public  enum StatusCode{
	        Success,
	        Fail,
	        Other
	    }

	    public Date TriggedTime ;

	    public String CommandName ;

	    public StatusCode Status ;

	    public String ResponseBody ;
	    public String EXResponseBody ;
	    public String VersionResponseBody ;

	    public String ActualResponseBody ;
	    public String pcykResponseBody ;
	    public String OriginBody ;

	    public  ResponseEventArgs(String rspmsg)
	    {
	        String[] str = rspmsg.split(" ");
	        String[] str2 = rspmsg.split("^");
	        String[] str3 = rspmsg.split("~");
	        if(rspmsg.length()==0)
	        {
	            return;
	        }
	        CommandName = str[0];
	        Status = str[1].equalsIgnoreCase("SUCCESS") ? StatusCode.Success : (str[1].equalsIgnoreCase("FAIL")?StatusCode.Fail:StatusCode.Other);
	        if (str.length > 2)
	        {
	            ResponseBody = str[2];
	        }

	        if (str.length > 3)
	        {
	            EXResponseBody = str[3];
	        }

	        if (str.length > 4)
	        {
	            VersionResponseBody = str[4];
	        }

	        if(str2.length>1){
	            ActualResponseBody = str2[1];
	        }
	        if (str3.length>1)
	        {
	            pcykResponseBody = str3[1];

	        }

	        OriginBody = rspmsg;

	        TriggedTime = new Date();
	    }

	    @Override
	    public String toString() {
	        return "ResponseEventArgs{" +
	                "TriggedTime=" + TriggedTime +
	                ", CommandName='" + CommandName + '\'' +
	                ", Status=" + Status +
	                ", ResponseBody='" + ResponseBody + '\'' +
	                ", EXResponseBody='" + EXResponseBody + '\'' +
	                ", VersionResponseBody='" + VersionResponseBody + '\'' +
	                ", ActualResponseBody='" + ActualResponseBody + '\'' +
	                ", pcykResponseBody='" + pcykResponseBody + '\'' +
	                ", OriginBody='" + OriginBody + '\'' +
	                '}';
	    }
}
