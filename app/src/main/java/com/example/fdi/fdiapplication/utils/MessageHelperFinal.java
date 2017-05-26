package com.example.fdi.fdiapplication.utils;

public class MessageHelperFinal 
{
	/**
	 * 心跳信息
	 * @param secret 秘钥
	 * @return 返回包装好的协议
	 */
	public static String HeartBeatMessage(String secret) 
	{
		return "LoginBeat "+secret;
	}

	/**
	 * 返回接收到服务器消息的guid
	 * @param guid
	 * @return
	 */
	public static String IGetMessage(String guid) 
	{
	    return "IGet "+guid;	
	}

	/**
	 * Check消息
	 * @param secret
	 * @param iport
	 * @return
	 */
	public static String CheckMessage(String secret,String iport) 
	{
	    return "Check "+secret+" "+System.currentTimeMillis()+" "+GetMacAddr_Local.GetMacLocal()+"|"+iport;
	}

	/**
	 * 登陆消息
	 * @param Userid
	 * @param Password
	 * @return
	 */
	public static String LoginMessage(String Userid ,String Password) 
	{
		return "Login "+Userid+" "+Password;
	}

	/**
	 * 添加实际入金
	 * @param UserID  用户ID
	 * @param inmoney 入金Money
	 * @param secret  加密后secret
	 * @param CodeX   可逆base64
	 * @param TradeID 交易ID
	 * @return 返回string
	 */
	public static String InMoneyMessage(String UserID,int inmoney,String secret,String CodeX,String TradeID) 
	{
		return "InMoneyByBank "+UserID+" "+inmoney+" "+secret+" "+CodeX+" "+TradeID;
	}
	
}
