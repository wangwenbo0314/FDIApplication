package com.example.fdi.fdiapplication.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.example.fdi.fdiapplication.Services.DataReciveEvent;
import com.example.fdi.fdiapplication.Services.EventListener;
import com.example.fdi.fdiapplication.Services.SystemEvent;
import com.example.fdi.fdiapplication.Services.SystemListener;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

public class UDPClientHelper {
    public String HostIP = "101.37.86.94";//获取通信目标IP
    int port = 50608;//获取端口号
    private static UDPClientHelper udpHelper = new UDPClientHelper();//单例
    private DatagramPacket dpRcv = null, dpSend = null;//接受和发送的包
    private byte[] msgRcv = new byte[1024];//字节转换
    boolean networkGood = true;
    Object networkGoodObject = new Object();
    private boolean IsConnection = false;//是否连接

    public UDPClientHelper() {
        IsConnection = Init();
    }//构造函数

    /*
     * 获取是否连接
     * @return 是否链接
     */
    public boolean GetIsConnection() {
        return IsConnection;
    }

    /*
     * 析构函数
     */
    @Override
    protected void finalize() throws Throwable {
        Close();
        super.finalize();
    }

    /*
     * 关闭连接方法！
     */
    public void Close() {
        UnInit();
    }

    /*
     * 反向初始化
     */
    private void UnInit() {
        heartBeatTimer.cancel();
        if (threadReceive1 != null)
            threadReceive1.interrupt();
        if (threadReceive2 != null)
            threadReceive2.interrupt();
        if (threadReceive3 != null)
            threadReceive3.interrupt();
        if (clientsocket != null)
            clientsocket.close();
        clientsocket = null;
    }

    /*
     * 单例模式获取UDPClientHelper实例
     * @return UDPClientHelper
     */
    public static UDPClientHelper getUDPClientHelperInstance() {

        if (udpHelper == null) {

            udpHelper = new UDPClientHelper();
        }
        return udpHelper;
    }


    public boolean Init() {

        try {

            heartBeatTimer = new Timer();
            heartBeatTimer.schedule(new TimerTask() {
                public void run() {
                    SendHeartBeatMessage();
                }
            }, 0, 30000);

            if (port_super != 0) {
                clientsocket = new DatagramSocket();
                clientsocket.bind(new InetSocketAddress("101.37.86.94", port_super));//后期修改
            } else {
                clientsocket = new DatagramSocket();
            }
            clientsocket.connect(new InetSocketAddress(HostIP, port));
            threadReceive1 = new Thread(new Runnable() {
                public void run() {
                    reciveData();
                }
            });
            threadReceive1.start();

            threadReceive2 = new Thread(new Runnable() {
                public void run() {
                    reciveData();
                }
            });
            threadReceive2.start();

            threadReceive3 = new Thread(new Runnable() {
                public void run() {
                    reciveData();
                }
            });
            threadReceive3.start();

            networkGood = true;

            if (port_super == 0) {
                port_super = clientsocket.getLocalPort();
            }

            SendCheckMessage();

            return true;

        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }

    }


    private void SendCheckMessage() {
        try {
            SendMainMessageASync(MessageHelperFinal.CheckMessage("|", String.valueOf(clientsocket.getLocalPort())));
            Log.i("Tag02", "已经发送check检查！" + "本地端口：" + clientsocket.getLocalPort());
        } catch (Exception e)

        {
            synchronized (networkGoodObject) {
                networkGood = false;
            }
            e.printStackTrace();
        }
    }


    private void SendHeartBeatMessage() {
        try {
            synchronized (networkGoodObject) {
                if (networkGood) {
                    networkGood = SendMessage(MessageHelperFinal.HeartBeatUDPMessage(String.valueOf(clientsocket.getLocalPort())));
                } else {
                    synchronized (SendSocket) {
                        UnInit();
                        IsConnection = Init();
                        networkGood = true;
                    }
                }
            }
        } catch (Exception ex) {
            synchronized (networkGoodObject) {
                networkGood = false;
            }

        }
    }


    /**
     * 读取服务器发过来的信息
     *
     * @return
     */
    @SuppressWarnings("finally")
    private String receiveMessage() {
        String receiveMessage = "";
        try {
            synchronized (receiveMessage) {
                dpRcv = new DatagramPacket(msgRcv, msgRcv.length);
                clientsocket.receive(dpRcv);
                receiveMessage = new String(dpRcv.getData(), dpRcv.getOffset(), dpRcv.getLength());
                Log.i("Tag12", receiveMessage);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            UnInit();
            IsConnection = Init();
        } finally {
            return receiveMessage;
        }
    }


    private void reciveData() {

        while (!isExit) {
            String receiveData = "";
            receiveData = receiveMessage();

            if (receiveData == "") {
                continue;
            } else {
                Log.i("Tag01", receiveMessage());
                SystemEvent systemEvent = new DataReciveEvent(new ResponseEventArgs(receiveData));
                SystemListener systemListener = new EventListener();
                systemEvent.addDemoListener(systemListener);
                systemEvent.notifyDemoEvent();
            }
        }
    }


    /**
     * 异步返回消息是否发送成功
     *
     * @param message
     */
    public void SendMainMessageASync(final String message) {
        new Thread(new Runnable() {
            public void run() {
                SendMessage(message);
            }
        }).start();
    }


    /**
     * 同步发送信息
     *
     * @param msg 消息体
     * @return 返回是否成功
     */
    private boolean SendMessage(String msg) {
        try {
            synchronized (SendSocket) {
                Log.i("Tag11", msg);
                InetAddress serverAddr = InetAddress.getByName(HostIP);
                dpSend = new DatagramPacket(msg.getBytes(), msg.getBytes().length, serverAddr, port);
                clientsocket.send(dpSend);
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * 承载对象Client
     */
    DatagramSocket clientsocket = null;
    Object SendSocket = new Object();

    int port_super = 0;

    /**
     * 接收服务器信息处理线程
     */
    Thread threadReceive1 = null;
    Thread threadReceive2 = null;
    Thread threadReceive3 = null;

    boolean isExit = false;

    static Timer heartBeatTimer;

    public String ipport;
}
