package cn.iprograms.petfun.client;


import android.util.Log;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by JaCy on 2017/11/14.
 */

class Send implements Runnable {
    private String TAG="TESTTEST";

    private Socket socket;
    private OutputStream outputStream;//向Socket流入
    private PrintStream printStream;
    private String data;

    Send(Socket s, String d){
        this.socket=s;
        this.data=d;
        try {
            outputStream=socket.getOutputStream();
            printStream=new PrintStream(outputStream);
        }catch (Exception e){
            Log.d(TAG, "---------------------------未与服务器连接，无法发送数据");
            DataSet.setDataFlag();
        }
    }

    public void run() {
        try {
            printStream.println(data);
        }catch (Exception e){
            Log.d(TAG, "---------------------------与服务器断开，无法发送数据");
            DataSet.setDataFlag();
        }
    }
}
