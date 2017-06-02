package com.example.fdi.fdiapplication.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 获取本机物理地址
 *
 * @author Moyi
 */
public class GetMacAddr_Local {

    /**
     * 获取当前物理地址
     *
     * @return 物理地址字符串
     */
    @SuppressWarnings("finally")
    public static String GetMacLocal() {
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
        } finally {
            return address.trim();
        }

    }
    /**
     * 获取手机的MAC地址
     *
     * @return
     */

    protected static InetAddress getLocalInetAddress() {
        InetAddress ip = null;
        try {
            Enumeration<NetworkInterface> en_netInterface = NetworkInterface.getNetworkInterfaces();
            while (en_netInterface.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) en_netInterface.nextElement();
                Enumeration<InetAddress> en_ip = ni.getInetAddresses();
                while (en_ip.hasMoreElements()) {
                    ip = en_ip.nextElement();
                    if (!ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1)
                        break;
                    else
                        ip = null;
                }

                if (ip != null) {
                    break;
                }
            }
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ip;
    }

    public static String getMacAddress() /* throws UnknownHostException */{
        String strMacAddr = null;
        try {
            InetAddress ip = getLocalInetAddress();

            byte[] b = NetworkInterface.getByInetAddress(ip).getHardwareAddress();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < b.length; i++) {
                if (i != 0) {
                    buffer.append('-');
                }

                String str = Integer.toHexString(b[i] & 0xFF);
                buffer.append(str.length() == 1 ? 0 + str : str);
            }
            strMacAddr = buffer.toString().toUpperCase();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return strMacAddr;
    }

}
