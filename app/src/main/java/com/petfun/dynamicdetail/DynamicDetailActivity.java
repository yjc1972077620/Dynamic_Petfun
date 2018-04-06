package com.petfun.dynamicdetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.petfun.dynamic.DynamicItem;
import com.petfun.dynamic.R;
import com.petfun.dynamic.ShowBigPicActivity;

import java.util.ArrayList;
import java.util.List;

import cn.iprograms.petfun.client.DataHandle;
import cn.iprograms.petfun.client.DataStandard;
import de.hdodenhof.circleimageview.CircleImageView;

public class DynamicDetailActivity extends AppCompatActivity {
    private Context context=this;
    private static  final int SUCCESS=1;
    private  static  final int FAILUE=2;
    private  DynamicItem item;
    private CircleImageView user_image;
    private ListView listView;
    private DynamicDetailData dynamicDetailData;
    private List<DynamicDetails> mlist=new ArrayList<>();
    private ImageView back,dianzanimage;
    private  CommentAdapter commentAdapter;
    private SwipeRefreshLayout refreshLayout;
    private  ImageView detail_img1,detail_img2,detail_img3,detail_img4,detail_img5,detail_img6,detail_img7,detail_img8,detail_img9;
    private  TextView title,user_name,content,date,dianzancount,commentcount;
    private LinearLayout row1,row2,row3;

    private EditText comment_edit;
    private Button subment_comment;
    private  int flag=0;
    private  int imagecount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_detail);



    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        refreshLayout =(SwipeRefreshLayout)findViewById(R.id.comment_refresh);
        comment_edit=(EditText)findViewById(R.id.comment_edit);
        subment_comment=(Button)findViewById(R.id.submit_comment);
        listView=(ListView)findViewById(R.id.comment_list);

        title= (TextView) findViewById(R.id.detailtitle);


        LayoutInflater inflater=this.getLayoutInflater();
        View header=inflater.inflate(R.layout.header,null);
        listView.addHeaderView(header);

        user_image=(CircleImageView)header.findViewById(R.id.user_image);
        user_name=(TextView)header.findViewById(R.id.user_name);
        content=(TextView)header.findViewById(R.id.content);
        date=(TextView)header.findViewById(R.id.date);
        dianzanimage=(ImageView)header.findViewById(R.id.dianzan_image);
        dianzancount=(TextView)header.findViewById(R.id.dianzan_count);

        refreshLayout.setRefreshing(true);
        commentAdapter =new CommentAdapter(this);
        //主活动传输来的数据
        item= (DynamicItem) getIntent().getSerializableExtra("Dynamicdata");
        new Thread(new Runnable() {
            @Override
            public void run() {


                String dynamic_id=item.getDynamicId();
                dynamicDetailData =new DynamicDetailData(dynamic_id);
                dynamicDetailData.initList();
                mlist=dynamicDetailData.getData();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(dynamicDetailData.getData()!=null)
                        {
                            mlist=dynamicDetailData.getData();
                        }
                        commentAdapter.setlist(mlist);
                        refresh();
                    }
                });
            }
        }).start();
        //刷新事件方法的重写
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                Toast.makeText(getApplicationContext(),"正在刷新",Toast.LENGTH_SHORT).show();
                refresh();

            }
        });



        listView.setAdapter(commentAdapter);

        Log.d("DETAIL", "onCreate: "+item);


        subment_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(comment_edit.getText().toString().length()==0)
                {
                    Toast.makeText(context,"评论不能为空",Toast.LENGTH_LONG).show();
                }
                else
                {
                    DataHandle.sendData("<dynamiccomment><divide>"
                            +DataStandard.USER_ID
                            +"<divide>"+comment_edit.getText().toString()
                            +"<divide>"+item.getDynamicId());
                    DataHandle.setOnReceiverListener(new DataHandle.CommentHandler() {
                        @Override
                        public void listenerEnd() {

                        }

                        @Override
                        public void sendSuccess() {

                        }

                        @Override
                        public void netException() {

                        }
                    });
                    dynamicDetailData.initList();
                    if(dynamicDetailData.getData()!=null)
                    {
                        mlist=dynamicDetailData.getData();
                    }

                    comment_edit.setText("");
                    refresh();
                    Toast.makeText(context,"发送成功",Toast.LENGTH_LONG).show();


                }

            }
        });

        dianzanimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (flag)
                {
                    case 0:
                    {
                        int count=item.getLikes()+1;
                        dianzancount.setText(count+"人点赞");
                        dianzanimage.setImageResource(R.drawable.love_next);
                        flag=1;
                    }
                    break;
                    case 1:
                    {

                        dianzancount.setText(item.getLikes()+"人点赞");
                        dianzanimage.setImageResource(R.drawable.love_pre);
                        flag=0;
                    }
                }

            }
        });

        dianzancount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (flag)
                {
                    case 0:
                    {
                        int count=item.getLikes()+1;
                        dianzancount.setText(count+"人点赞");
                        dianzanimage.setImageResource(R.drawable.love_next);
                        flag=1;
                    }
                    break;
                    case 1:
                    {
                        dianzancount.setText(item.getLikes()+"人点赞");
                        dianzanimage.setImageResource(R.drawable.love_pre);
                        flag=0;
                    }
                    break;
                }
            }
        });
        commentcount=(TextView)header.findViewById(R.id.comment_count);
        Glide.with(this).load(item.getHeadLink()).into(user_image);
        user_name.setText(item.getUserName());
        content.setText(item.getContent());
        dianzancount.setText(item.getLikes()+"人点赞");
        commentcount.setText(item.getComments()+"条评论");
        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        detail_img1=(ImageView) header.findViewById(R.id.detail_image1);
        detail_img2=(ImageView) header.findViewById(R.id.detail_image2);
        detail_img3=(ImageView) header.findViewById(R.id.detail_image3);
        detail_img4=(ImageView) header.findViewById(R.id.detail_image4);
        detail_img5=(ImageView) header.findViewById(R.id.detail_image5);
        detail_img6=(ImageView) header.findViewById(R.id.detail_image6);
        detail_img7=(ImageView) header.findViewById(R.id.detail_image7);
        detail_img8=(ImageView) header.findViewById(R.id.detail_image8);
        detail_img9=(ImageView) header.findViewById(R.id.detail_image9);



        row1=(LinearLayout)header.findViewById(R.id.detailimage_row1);
        row2=(LinearLayout)header.findViewById(R.id.detailimage_row2);
        row3=(LinearLayout)header.findViewById(R.id.detailimage_row3);
        if(item.getImageList()!=null)
        {
            imagecount=item.getImageList().size();
        }

       List<String> image=item.getImageList();
        //根据接收图片数量隐藏imageview
        switch(imagecount)
        {
            case 0:
            {
                detail_img1.setVisibility(View.GONE);
                detail_img2.setVisibility(View.GONE);
                detail_img3.setVisibility(View.GONE);
                detail_img4.setVisibility(View.GONE);
                detail_img5.setVisibility(View.GONE);
                detail_img6.setVisibility(View.GONE);
                detail_img7.setVisibility(View.GONE);
                detail_img8.setVisibility(View.GONE);
                detail_img9.setVisibility(View.GONE);
            }
                break;
            case 1:
            {
                detail_img1.setVisibility(View.VISIBLE);
                detail_img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=0;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                detail_img2.setVisibility(View.INVISIBLE);
                detail_img3.setVisibility(View.INVISIBLE);
                row2.setVisibility(View.GONE);
                row3.setVisibility(View.GONE);
                Glide.with(this).load(image.get(0)).into(detail_img1);

            }
            break;
            case 2:
            {
                detail_img1.setVisibility(View.VISIBLE);
                detail_img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=0;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(0)).into(detail_img1);
                detail_img2.setVisibility(View.VISIBLE);
                detail_img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=1;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(1)).into(detail_img2);
                detail_img3.setVisibility(View.INVISIBLE);
                row2.setVisibility(View.GONE);
                row3.setVisibility(View.GONE);
            }
            break;
            case 3:
            {
                detail_img1.setVisibility(View.VISIBLE);
                detail_img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=0;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(0)).into(detail_img1);
                detail_img2.setVisibility(View.VISIBLE);
                detail_img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=1;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(1)).into(detail_img2);
                detail_img3.setVisibility(View.VISIBLE);
                detail_img3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=2;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(2)).into(detail_img3);
                row2.setVisibility(View.GONE);
                row3.setVisibility(View.GONE);
            }
            break;
            case 4:
            {
                detail_img1.setVisibility(View.VISIBLE);
                detail_img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=0;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(0)).into(detail_img1);
                detail_img2.setVisibility(View.VISIBLE);
                detail_img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=1;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(1)).into(detail_img2);
                detail_img3.setVisibility(View.VISIBLE);
                detail_img3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=2;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(2)).into(detail_img3);
                detail_img4.setVisibility(View.VISIBLE);
                detail_img4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=3;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(3)).into(detail_img4);
                detail_img5.setVisibility(View.INVISIBLE);
                detail_img6.setVisibility(View.INVISIBLE);
                row3.setVisibility(View.GONE);
            }
            break;
            case 5:
            {
                detail_img1.setVisibility(View.VISIBLE);
                detail_img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=0;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(0)).into(detail_img1);
                detail_img2.setVisibility(View.VISIBLE);
                detail_img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=1;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(1)).into(detail_img2);
                detail_img3.setVisibility(View.VISIBLE);
                detail_img3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=2;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(2)).into(detail_img3);
                detail_img4.setVisibility(View.VISIBLE);
                detail_img4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=3;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(3)).into(detail_img4);
                detail_img5.setVisibility(View.VISIBLE);
                detail_img5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=4;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(4)).into(detail_img5);
                detail_img6.setVisibility(View.INVISIBLE);
                row3.setVisibility(View.GONE);
            }
            break;
            case 6:
            {
                detail_img1.setVisibility(View.VISIBLE);
                detail_img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=0;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(0)).into(detail_img1);
                detail_img2.setVisibility(View.VISIBLE);
                detail_img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=1;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(1)).into(detail_img2);
                detail_img3.setVisibility(View.VISIBLE);
                detail_img3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=2;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(2)).into(detail_img3);
                detail_img4.setVisibility(View.VISIBLE);
                detail_img4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=3;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(3)).into(detail_img4);
                detail_img5.setVisibility(View.VISIBLE);
                detail_img5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=4;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(4)).into(detail_img5);
                detail_img6.setVisibility(View.VISIBLE);
                detail_img6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=5;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(5)).into(detail_img6);
                row3.setVisibility(View.GONE);
            }
            break;
            case 7:
            {
                detail_img1.setVisibility(View.VISIBLE);
                detail_img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=0;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(0)).into(detail_img1);
                detail_img2.setVisibility(View.VISIBLE);
                detail_img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=1;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(1)).into(detail_img2);
                detail_img3.setVisibility(View.VISIBLE);
                detail_img3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=2;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(2)).into(detail_img3);
                detail_img4.setVisibility(View.VISIBLE);
                detail_img4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=3;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(3)).into(detail_img4);
                detail_img5.setVisibility(View.VISIBLE);
                detail_img5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=4;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(4)).into(detail_img5);
                detail_img6.setVisibility(View.VISIBLE);
                detail_img6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=5;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(5)).into(detail_img6);
                detail_img7.setVisibility(View.VISIBLE);
                detail_img7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=6;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(6)).into(detail_img7);
                detail_img8.setVisibility(View.INVISIBLE);
                detail_img9.setVisibility(View.INVISIBLE);
            }
            break;
            case 8:
            {
                detail_img1.setVisibility(View.VISIBLE);
                detail_img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=0;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(0)).into(detail_img1);
                detail_img2.setVisibility(View.VISIBLE);
                detail_img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=1;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(1)).into(detail_img2);
                detail_img3.setVisibility(View.VISIBLE);
                detail_img3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=2;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(2)).into(detail_img3);
                detail_img4.setVisibility(View.VISIBLE);
                detail_img4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=3;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(3)).into(detail_img4);

                detail_img5.setVisibility(View.VISIBLE);
                detail_img5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                    DynamicItem itemdata=item;
                    String[] piclist= itemdata.getImageList().toArray(new String[0]);
                    intent.putExtra("piclist",piclist);
                    int index=4;
                    intent.putExtra("position",index);
                    startActivity(intent);

                }
            });
                Glide.with(this).load(image.get(4)).into(detail_img5);
                detail_img6.setVisibility(View.VISIBLE);
                detail_img6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=5;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(5)).into(detail_img6);
                detail_img7.setVisibility(View.VISIBLE);
                detail_img7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=6;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(6)).into(detail_img7);
                detail_img8.setVisibility(View.VISIBLE);
                detail_img8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=7;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(7)).into(detail_img8);
                detail_img9.setVisibility(View.INVISIBLE);
            }
            break;
            case 9:
            {
                detail_img1.setVisibility(View.VISIBLE);
                detail_img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=0;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(0)).into(detail_img1);
                detail_img2.setVisibility(View.VISIBLE);
                detail_img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=1;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(1)).into(detail_img2);
                detail_img3.setVisibility(View.VISIBLE);
                detail_img3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=2;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(2)).into(detail_img3);
                detail_img4.setVisibility(View.VISIBLE);
                detail_img4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=3;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(3)).into(detail_img4);
                detail_img5.setVisibility(View.VISIBLE);
                detail_img5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=4;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(4)).into(detail_img5);
                detail_img6.setVisibility(View.VISIBLE);
                detail_img6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=5;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(5)).into(detail_img6);
                detail_img7.setVisibility(View.VISIBLE);
                detail_img7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=6;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(6)).into(detail_img7);
                detail_img8.setVisibility(View.VISIBLE);
                detail_img8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=7;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(7)).into(detail_img8);
                detail_img9.setVisibility(View.VISIBLE);
                detail_img9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DynamicDetailActivity.this,ShowBigPicActivity.class);

                        DynamicItem itemdata=item;
                        String[] piclist= itemdata.getImageList().toArray(new String[0]);
                        intent.putExtra("piclist",piclist);
                        int index=8;
                        intent.putExtra("position",index);
                        startActivity(intent);

                    }
                });
                Glide.with(this).load(image.get(8)).into(detail_img9);
            }
            break;
            default:
                break;
        }


    }

    @Override
    public void onBackPressed() {
        finish();
    }


    public void refresh()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    dynamicDetailData.initList();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mlist=dynamicDetailData.getData();
                        commentAdapter.setlist(mlist);
                        commentAdapter.notifyDataSetChanged();
                        refreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

}
