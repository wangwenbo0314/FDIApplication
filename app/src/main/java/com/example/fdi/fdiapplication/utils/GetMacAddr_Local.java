package com.example.fdi.fdiapplication.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 获取本机物理地址
 * @author Moyi
 *
 */
public class GetMacAddr_Local 
{

	/**
	 * 获取当前物理地址
	 * @return 物理地址字符串
	 */
	@SuppressWarnings("finally")
	public  static String GetMacLocal() 
	{
		String address = "";

		try {

			ProcessBuilder pb = new ProcessBuilder("ipconfig", "/all");

			Process p = pb.start();

			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line;

			while ((line = br.readLine()) != null) {

				if (line.indexOf("Physical Address") != -1) {

					int index = line.indexOf(":");

					address = line.substring(index + 1);

					break;

				}

			}

			br.close();

		} catch (IOException e) {
             e.printStackTrace();
		}
		finally 
		{
			return address.trim();
		}

	}

}
