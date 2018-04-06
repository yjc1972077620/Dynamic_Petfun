package com.petfun.dynamic.addpic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.petfun.dynamic.R;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.iprograms.petfun.client.DataHandle;
import cn.iprograms.petfun.client.DataStandard;

/**
 * 添加图片的效果
 */
public class AddPictureActivity extends AppCompatActivity {
    private Context context=this;
    private TextView cancel,submit;
    private EditText edit;
    private Context mContext;
    private GridView gridView;
    private ArrayList<String> mPicList = new ArrayList<>(); //上传的图片凭证的数据源
    private GridViewAdapter mGridViewAddImgAdapter; //展示上传的图片的适配器


    private   static final  String HOST="139.199.168.75";
    private   static final  int  PORT=9080;
    private static final String PATH="http://169.254.251.103:8888";
    private static  final int SUCCESS=1;
    private  static  final int FAILUE=2;
    private  static  final int ERROR=3;
    private  static  final int SHOW=4;

    private static  final String HEAD="##HEAD";
    private static  final String END="##END";
    private static  final String PIC="##PIC:";
    private static  final String WORDS="##WORDS:";
    private  List<String> pic=new ArrayList<>();


    private Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what)
            {
                case SUCCESS:
                    Toast.makeText(context,"上传成功",Toast.LENGTH_LONG).show();
                    break;
                case FAILUE:
                    Toast.makeText(context,"上传失败",Toast.LENGTH_LONG).show();
                    break;
                case ERROR:
                    Toast.makeText(context,"请求出错",Toast.LENGTH_LONG).show();
                    break;
                case SHOW:
                   edit.setText("");
                    break;
            }
        };
    };
    public List<String> path = new ArrayList<>();

   public Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_picture);

        mContext = this;
        gridView = (GridView) findViewById(R.id.gridView);
        initGridView();
        cancel= (TextView) findViewById(R.id.cancel_add);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        submit=(TextView)findViewById(R.id.submit);
        edit=(EditText)findViewById(R.id.edit);
        path=mGridViewAddImgAdapter.getmList();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket=new Socket(HOST,PORT);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                String date=df.format(System.currentTimeMillis());
                String dynamicId=DataStandard.USER_ID+date;
                if(edit.getText().toString().length()!=0&&path.size()!=0)
                {
                    DataHandle.sendData("<dynamic><divide>"+DataStandard.USER_ID
                            +"<divide>"+edit.getText().toString()
                            +"<divide>"+dynamicId+"<divide>"+path.size());
                    Log.d("picsize", "onClick: "+path.size());
                    DataHandle.setOnReceiverListener(new DataHandle.DynamicHandler() {
                        @Override
                        public void listenerEnd() {

                        }

                        @Override
                        public void sendSuccess() {
                            handler.sendEmptyMessage(SUCCESS);
                        }

                        @Override
                        public void netException() {
//                        handler.sendEmptyMessage(ERROR);
                        }
                    });
                    new Thread(new sendpic(socket,path,dynamicId)).start();
//                Toast.makeText(context,"发表成功",Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
                else
                {
                    Toast.makeText(context,"发送内容不能为空",Toast.LENGTH_LONG).show();
                }



            }
        });

    }

//发送图片
public class sendpic implements Runnable
    {
        String dynamicId;
        Socket s;
        List<String> path=new ArrayList<>();
            public sendpic(Socket s,List<String> path,String di)
            {
                this.dynamicId=di;
                this.path=path;
                this.s=s;
            }
        @Override
        public void run() {
                if(s.isConnected())
                {
                    double pic_num=path.size();
                    for(int i=0;i<path.size();i++)
                    {
                        try {
                            DataOutputStream out = new DataOutputStream(s.getOutputStream());
                            BufferedInputStream bufin = new BufferedInputStream(new FileInputStream(path.get(i)));
                            String picName=dynamicId+i;
                            int picNameLenth=picName.getBytes().length;
                            byte[] bufdata = new byte[1024];
                            int len;

                            long pic_data_lenth=bufin.available();

//                            out.writeDouble(pic_num);
                            out.writeInt(picNameLenth);
                            out.write(picName.getBytes());
                            out.writeLong(pic_data_lenth);
                            while ((len = bufin.read(bufdata)) != -1)
                            {
                                out.write(bufdata, 0, len);
                                out.flush();
                            }
                            out.flush();
//                            out.close();
                            Log.d("testtest", "run: ----------------------------------"+i+"_____"+pic_num);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Log.d("pic_num", "run: "+pic_num);
                    }
                }

        }
    }


//通过http上传数据到服务器

    public void UploadData(Map<String,String> data_map)
    {
        StringBuilder sb=new StringBuilder();
        for(Map.Entry<String,String> entry:data_map.entrySet())
        {
            sb.append(entry.getKey()+"="+entry.getValue()+"&");
        }
        sb.delete(sb.length()-1,sb.length());
        try {
            URL url=new URL(PATH);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            DataOutputStream dos=new DataOutputStream(conn.getOutputStream());
            dos.write(sb.toString().getBytes());
            dos.flush();
            dos.close();

            if(conn.getResponseCode()==200)
            {
                InputStream is=conn.getInputStream();
                byte[] buffer=new byte[1024];
                int len=is.read(buffer,0,1024);
                if(len!=-1)
                {
                    String result=new String(buffer,0,len);
                    Log.d("uploadflag", "UploadData: "+result);
                    if(result.equals("ok"))
                    {
                        handler.sendEmptyMessage(SUCCESS);
                    }
                    else
                    {
                        handler.sendEmptyMessage(FAILUE);
                    }
                }else
                {
                    Log.d("uploadflag", "UploadData: "+"读取失败");
                    handler.sendEmptyMessage(ERROR);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //初始化展示上传图片的GridView
    private void initGridView() {
        mGridViewAddImgAdapter = new GridViewAdapter(mContext, mPicList);
        gridView.setAdapter(mGridViewAddImgAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == parent.getChildCount() - 1) {
                    //如果“增加按钮形状的”图片的位置是最后一张，且添加了的图片的数量不超过5张，才能点击
                    if (mPicList.size() == MainConstant.MAX_SELECT_PIC_NUM) {
                        //最多添加9张图片
                        viewPluImg(position);
                    } else {
                        //添加凭证图片
                        selectPic(MainConstant.MAX_SELECT_PIC_NUM - mPicList.size());
                    }
                } else {
                    viewPluImg(position);
                }
            }
        });
    }

    //查看大图
    private void viewPluImg(int position) {
        Intent intent = new Intent(mContext, PlusImageActivity.class);
        intent.putStringArrayListExtra(MainConstant.IMG_LIST, mPicList);
        intent.putExtra(MainConstant.POSITION, position);
        startActivityForResult(intent, MainConstant.REQUEST_CODE_MAIN);
    }

    /**
     * 打开相册或者照相机选择凭证图片，最多9张
     *
     * @param  maxTotal 最多选择的图片的数量
     */
    private void selectPic(int maxTotal) {
        PictureSelectorConfig.initMultiConfig(this, maxTotal);
    }

    // 处理选择的照片的地址
    private void refreshAdapter(List<LocalMedia> picList) {
        for (LocalMedia localMedia : picList) {
            //被压缩后的图片路径
            if (localMedia.isCompressed()) {
                String compressPath = localMedia.getCompressPath(); //压缩后的图片路径
                mPicList.add(compressPath); //把图片添加到将要上传的图片数组中
                mGridViewAddImgAdapter.notifyDataSetChanged();
//                Glide.with(this).load(mPicList.get(0)).into(pic);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    refreshAdapter(PictureSelector.obtainMultipleResult(data));
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    break;
            }
        }
        if (requestCode == MainConstant.REQUEST_CODE_MAIN && resultCode == MainConstant.RESULT_CODE_VIEW_IMG) {
            //查看大图页面删除了图片
            ArrayList<String> toDeletePicList = data.getStringArrayListExtra(MainConstant.IMG_LIST); //要删除的图片的集合
            mPicList.clear();
            mPicList.addAll(toDeletePicList);
            mGridViewAddImgAdapter.notifyDataSetChanged();
        }

    }
}
