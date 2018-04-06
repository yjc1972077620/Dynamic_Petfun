package cn.iprograms.petfun.client;

import android.util.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;


public class Client {
    private static String TAG="TESTTEST";
    private static Socket socket;
    private static final String address="139.199.168.75";
//    private static final String address="192.168.0.103";
    private static final int port=9010;
    public static void initClient(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                socket = new Socket();
                try {
                    Log.d(TAG, "----------正在连接服务器");
                    socket.connect(new InetSocketAddress(address,port),1000);
                    Log.d(TAG, "----------已经连接服务器");
                    socket.setSoTimeout(10000);
                    HeartBeat.Start();
                    new Thread(new Receive(socket)).start();//启动接收数据线程，一直运行
                }catch (IOException e){
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException ie){
                        //nothing
                    }
                    initClient();
                }
            }
        }).start();
    }
    static void closeClient(){
        try{
            if (socket!=null){
                socket.close();
                socket=null;
            }
        }catch (IOException e){
            //
        }
        HeartBeat.Stop();
    }
    static Socket getSocket(){
        return socket;
    }
}
