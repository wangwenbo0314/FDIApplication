package com.example.fdi.fdiapplication.utils;

import android.util.Log;

import com.example.fdi.fdiapplication.Services.DataReciveEvent;
import com.example.fdi.fdiapplication.Services.SystemEvent;
import com.example.fdi.fdiapplication.Services.SystemListener;
import com.example.fdi.fdiapplication.Services.EventListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/5/24 0024.
 * 当前Nio客户端需要长连接与交易服务器（需要修改TCP问题，需要与当前系统匹配）
 */

public class NioClientHelper {
    /**
     * 获取通信目标IP
     */
    public String HostIP = "101.37.86.94";

    /**
     * 获取端口号
     */
    int port = 50611;
    /**
     * 单例
     */
    private static NioClientHelper ncHelper = new NioClientHelper();

    boolean networkGood = true;
    Object networkGoodObject = new Object();
    /**
     * 是否连接
     */
    private boolean IsConnection = false;

    /**
     * 构造函数
     */
    public NioClientHelper() {
        IsConnection = Init();
    }


    /**
     * 获取是否连接
     *
     * @return 是否链接
     */
    public boolean GetIsConnection() {
        return IsConnection;
    }


    /**
     * 析构函数
     */
    @Override
    protected void finalize() throws Throwable {
        Close();
        super.finalize();
    }


    /**
     * 关闭连接方法！
     */
    public void Close() {
        UnInit();
    }


    /**
     * 反向初始化
     */
    private void UnInit() {
        heartBeatTimer.cancel();
        try {
            if (sr != null) sr.close();
            if (threadReceive1 != null)
                threadReceive1.interrupt();
            if (threadReceive2 != null)
                threadReceive2.interrupt();
            if (threadReceive3 != null)
                threadReceive3.interrupt();
            if (clientsocket != null)
                clientsocket.close();
            clientsocket = null;
            if (sw != null) sw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 单例模式获取NioClientHelper实例
     *
     * @return NioClientHelper
     */
    public static NioClientHelper getNioClientHelperInstance() {

        if (ncHelper == null) {

            ncHelper = new NioClientHelper();
        }
        return ncHelper;
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
                clientsocket = new Socket();
                clientsocket.bind(new InetSocketAddress("101.37.86.94", port_super));//后期修改
            } else {
                clientsocket = new Socket();
            }

            clientsocket.connect(new InetSocketAddress(HostIP, port));
            sr = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
            sw = new BufferedWriter(new OutputStreamWriter(clientsocket.getOutputStream()));

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
            Log.i("Tag02","已经发送check检查！" + "本地端口：" + clientsocket.getLocalPort());
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
                    networkGood = SendMessage(MessageHelperFinal.HeartBeatMessage("&"));
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
                receiveMessage = sr.readLine();
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
                Log.i("Tag01",receiveMessage());
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

                sw.write(msg + "\r\n");
                sw.flush();

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
    Socket clientsocket = null;
    Object SendSocket = new Object();

    int port_super = 0;

    /**
     * 接收服务器信息处理线程
     */
    Thread threadReceive1 = null;
    Thread threadReceive2 = null;
    Thread threadReceive3 = null;


    /**
     * 流对象
     */
    BufferedReader sr = null;
    BufferedWriter sw = null;


    boolean isExit = false;

    static Timer heartBeatTimer;

    public String ipport;
}
