package cn.iprograms.petfun.client;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by JaCy on 2017/11/14.
 */

class Receive implements Runnable {
    private Socket socket=null;
    private InputStream inputStream=null;//从Socket流出
    private InputStreamReader inputStreamReader=null;
    private BufferedReader bufferedReader=null;
    Receive(Socket s) {
        this.socket=s;
        try {
            inputStream=socket.getInputStream();
            inputStreamReader=new InputStreamReader(inputStream,"GBK");
        }catch (IOException e){
            Log.d("TESTTEST", "---------------------------------------程序未与服务器连接");
        }
        bufferedReader=new BufferedReader(inputStreamReader);
    }

    public void run() {
        String conent=null;
        while ((conent=readFromServer())!=null) {
            DataSet.setDataUtils(conent);//将接收到的数据全部储与数据集合类（间隔极短时间会取出对应数据）
            Log.d("TESTTEST", "---------------------------------------服务器发来消息："+conent);
        }
    }
    private String readFromServer() {//从服务器端读数据
        String content = null;
        try {
            content=bufferedReader.readLine();
        } catch (IOException e) {
            Log.d("TESTTEST", "---------------------------------------程序从服务器断开连接");
            Client.closeClient();
            Client.initClient();
        }
        return content;
    }
}
