package com.petfun.dynamic;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by yjc19 on 2017/12/3.
 */

public class MyBottomDialog extends Dialog {
    private static final String TAG = "MyButtomDialog";
    private int index;
    private String[] piclist;
    private ProgressBar mProgressBar;
    public MyBottomDialog(Context context) {
        this(context, R.style.MyAnimDialog);
    }

    public void setpicdetail(int index, String[] piclist) {
        this.index = index;
        this.piclist = piclist;
    }

    public MyBottomDialog(@NonNull final Context context, int themeResId) {
        super(context, themeResId);
        View view = getLayoutInflater().inflate(R.layout.dialog_item, null);
        view.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = piclist[index];
                Log.d("url", "onClick: " + url);

                downloadFile();

//                Toast.makeText(getContext(),"图片地址"+index+":"+url,Toast.LENGTH_LONG).show();
                onBackPressed();

            }
        });
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        super.setContentView(view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        getWindow().setAttributes(p);
        p.height = (int) (d.getHeight() * 0.2);
        p.width = d.getWidth();
        p.gravity = Gravity.LEFT | Gravity.BOTTOM;
        dialogWindow.setAttributes(p);
    }

    public void downloadFile()
    {
String url="https://upd13.sogoucdn.com/nstatic/img/logo.png?v=4";
        OkHttpUtils//
                .get()//
                .url(url)//
                .build()//
                .execute(new FileCallBack(Environment.getExternalStorageDirectory()+"petfun/download/",  "download1.jpg")//
                {

                    @Override
                    public void onBefore(Request request, int id)
                    {
                    }

                    @Override
                    public void inProgress(float progress, long total, int id)
                    {

                        Log.e(TAG, "inProgress :" + (int) (100 * progress));
                    }

                    @Override
                    public void onError(Call call, Exception e, int id)
                    {
                        Log.e(TAG, "onError :" + e.getMessage());
                    }

                    @Override
                    public void onResponse(File file, int id)
                    {
                        Log.e(TAG, "onResponse :" + file.getAbsolutePath());
                    }
                });
        Toast.makeText(getContext(),"下载成功",Toast.LENGTH_SHORT).show();
    }
}



//    public Bitmap getHttpBitmap(final String url)
//    {
//
//        final Bitmap[] bitmap = new Bitmap[1];
//        new Thread(){
//            @Override
//            public void run() {
//                try {
//                    URL picurl=new URL(url);
//                    HttpURLConnection connection=(HttpURLConnection)picurl.openConnection();
//                    connection.setRequestMethod("GET");
//                    int code=connection.getResponseCode();
//                    if(code==200)
//                    {
//                        InputStream in=connection.getInputStream();
//                        bitmap[0] = BitmapFactory.decodeStream(in);
//                        in.close();
//
//                    }
//
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//
//
//       return bitmap[0];
//    }
//
//    public void savepic(Bitmap bitmap)
//    {
//        String picname= Environment.getExternalStorageDirectory().getAbsolutePath()+"/petfun/userpic/"+ UUID.randomUUID().toString()+".jpg";
//        File file=new File(picname);
//        FileOutputStream out;
//        try {
//            out=new FileOutputStream(file);
//            bitmap.compress(Bitmap.CompressFormat.JPEG,100,out);
//            out.flush();
//            out.close();
//            Log.d("picpath", "savepic: "+picname);
//            Toast.makeText(getContext(),"图片已保存",Toast.LENGTH_LONG).show();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}

//
//
//    public void Bytes(String httpurl,String savepath) {
//        //new一个URL对象
//        URL url = null;
//        try {
//            url = new URL(httpurl);
//        } catch (MalformedURLException e) {
////            e.printStackTrace();
//            Log.d("exp", "savepic: "+"exp1");
//        }
//        //打开链接
//
//        try {
//            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//            //设置请求方式为"GET"
//            conn.setRequestMethod("GET");
//            //超时响应时间为5秒
//            conn.setConnectTimeout(5 * 1000);
////            if(conn.getResponseCode()==200)
////            {
////                System.out.println("请求失败");
////            }
//            InputStream  inStream = conn.getInputStream();
//            byte[] data = readInputStream(inStream);
//            //new一个文件对象用来保存图片，默认保存当前工程根目录
//            File imageFile = new File(savepath);
//            //创建输出流
//            FileOutputStream outStream = new FileOutputStream(imageFile);
//            //写入数据
//            outStream.write(data);
//            //关闭输出流
//            outStream.close();
//        } catch (IOException e) {
//            Log.d("exp", "savepic: "+"exp2");
////            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//    public static byte[] readInputStream(InputStream inStream){
//        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//        //创建一个Buffer字符串
//        byte[] buffer = new byte[1024];
//        //每次读取的字符串长度，如果为-1，代表全部读取完毕
//
//        //使用一个输入流从buffer里把数据读取出来
//        try {
//            int len = inStream.read(buffer);
//            Log.d("len", "readInputStream: "+len);
//            while( len != -1 )
//            {
//                //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
//                outStream.write(buffer, 0, 1024);
//                inStream.close();
//            }
//        } catch (IOException e) {
//            Log.d("exp", "savepic: "+"store failue");
//            e.printStackTrace();
//        }
//        //关闭输入流
//
//        //把outStream里的数据写入内存
//        return outStream.toByteArray();
//    }
//}
