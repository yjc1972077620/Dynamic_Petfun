package com.petfun.dynamic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ShowBigPicActivity extends AppCompatActivity implements View.OnLongClickListener {
    private ViewPager viewPager;
    private View view1,view2,view3,view4,view5,view6,view7,view8,view9;
    private PhotoView img1,img2,img3,img4,img5,img6,img7,img8,img9;
    private List<View> bigpiclist=new ArrayList<>();
   private String[] piclist;
   private int position;
   private int index;
    private Context context=this;
    private TextView picdetail;
    private TextView savepic,cancel;
    public static Handler mhandle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_big_pic);

        mhandle=new Handler();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        viewPager= (ViewPager) findViewById(R.id.bigpic);
         piclist=  getIntent().getStringArrayExtra("piclist");
         position=getIntent().getIntExtra("position",0);
        index=viewPager.getCurrentItem();

        LayoutInflater inflater=getLayoutInflater();

        view1=inflater.inflate(R.layout.bigimg,null);
        view2=inflater.inflate(R.layout.bigimg,null);
        view3=inflater.inflate(R.layout.bigimg,null);
        view4=inflater.inflate(R.layout.bigimg,null);
        view5=inflater.inflate(R.layout.bigimg,null);
        view6=inflater.inflate(R.layout.bigimg,null);
        view7=inflater.inflate(R.layout.bigimg,null);
        view8=inflater.inflate(R.layout.bigimg,null);
        view9=inflater.inflate(R.layout.bigimg,null);




        int count=piclist.length;
        for(int i=0;i<count;i++)
        {
            System.out.println("+++++++++++++"+piclist[i]);
            System.out.println(count);
        }





        switch(count)
        {
            case 1:
            {
                img1= (PhotoView) view1.findViewById(R.id.BigImg);
                img1.enable();
                img1.setOnLongClickListener(this);
                Glide.with(context).load(piclist[0]).into(img1);
                picdetail=(TextView)view1.findViewById(R.id.picdetail);
                picdetail.setText("1/"+count);
                bigpiclist.add(view1);

                viewPager.setAdapter(new MyAdapter());
                viewPager.setCurrentItem(position);
                img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                break;
            }
            case 2:
            {

                img1= (PhotoView) view1.findViewById(R.id.BigImg);
                img1.enable();
                img1.setOnLongClickListener(this);
                img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                Glide.with(this).load(piclist[0]).into(img1);
                picdetail=(TextView)view1.findViewById(R.id.picdetail);
                picdetail.setText("1/"+count);
                bigpiclist.add(view1);

                img2= (PhotoView) view2.findViewById(R.id.BigImg);
                img2.enable();
                img2.setOnLongClickListener(this);
                img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                Glide.with(this).load(piclist[1]).into(img2);
                picdetail=(TextView)view2.findViewById(R.id.picdetail);
                picdetail.setText("2/"+count);
                bigpiclist.add(view2);


                viewPager.setAdapter(new MyAdapter());
                viewPager.setCurrentItem(position);
                break;
            }
            case 3:
            {

                img1= (PhotoView) view1.findViewById(R.id.BigImg);
                img1.enable();
                img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img1.setOnLongClickListener(this);
                Glide.with(this).load(piclist[0]).into(img1);
                picdetail=(TextView)view1.findViewById(R.id.picdetail);
                picdetail.setText("1/"+count);
                bigpiclist.add(view1);

                img2= (PhotoView) view2.findViewById(R.id.BigImg);
                img2.enable();
                img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img2.setOnLongClickListener(this);
                Glide.with(this).load(piclist[1]).into(img2);
                picdetail=(TextView)view2.findViewById(R.id.picdetail);
                picdetail.setText("2/"+count);
                bigpiclist.add(view2);

                img3= (PhotoView) view3.findViewById(R.id.BigImg);
                img3.enable();
                img3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img3.setOnLongClickListener(this);
                Glide.with(this).load(piclist[2]).into(img3);
                picdetail=(TextView)view3.findViewById(R.id.picdetail);
                picdetail.setText("3/"+count);
                bigpiclist.add(view3);


                viewPager.setAdapter(new MyAdapter());
                viewPager.setCurrentItem(position);
                break;
            }
            case 4:
            {
                img1= (PhotoView) view1.findViewById(R.id.BigImg);
                img1.enable();
                img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img1.setOnLongClickListener(this);
                Glide.with(this).load(piclist[0]).into(img1);
                picdetail=(TextView)view1.findViewById(R.id.picdetail);
                picdetail.setText("1/"+count);
                bigpiclist.add(view1);

                img2= (PhotoView) view2.findViewById(R.id.BigImg);
                img2.enable();
                img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img2.setOnLongClickListener(this);
                Glide.with(this).load(piclist[1]).into(img2);
                picdetail=(TextView)view2.findViewById(R.id.picdetail);
                picdetail.setText("2/"+count);
                bigpiclist.add(view2);

                img3= (PhotoView) view3.findViewById(R.id.BigImg);
                img3.enable();
                img3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img3.setOnLongClickListener(this);
                Glide.with(this).load(piclist[2]).into(img3);
                picdetail=(TextView)view3.findViewById(R.id.picdetail);
                picdetail.setText("3/"+count);
                bigpiclist.add(view3);

                img4= (PhotoView) view4.findViewById(R.id.BigImg);
                img4.enable();
                img4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img4.setOnLongClickListener(this);
                Glide.with(this).load(piclist[3]).into(img4);
                picdetail=(TextView)view4.findViewById(R.id.picdetail);
                picdetail.setText("4/"+count);
                bigpiclist.add(view4);


                viewPager.setAdapter(new MyAdapter());
                viewPager.setCurrentItem(position);
                break;
            }
            case 5:
            {
                img1= (PhotoView) view1.findViewById(R.id.BigImg);
                img1.enable();
                img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img1.setOnLongClickListener(this);
                Glide.with(this).load(piclist[0]).into(img1);
                picdetail=(TextView)view1.findViewById(R.id.picdetail);
                picdetail.setText("1/"+count);
                bigpiclist.add(view1);

                img2= (PhotoView) view2.findViewById(R.id.BigImg);
                img2.enable();
                img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img2.setOnLongClickListener(this);
                Glide.with(this).load(piclist[1]).into(img2);
                picdetail=(TextView)view2.findViewById(R.id.picdetail);
                picdetail.setText("2/"+count);
                bigpiclist.add(view2);

                img3= (PhotoView) view3.findViewById(R.id.BigImg);
                img3.enable();
                img3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img3.setOnLongClickListener(this);
                Glide.with(this).load(piclist[2]).into(img3);
                picdetail=(TextView)view3.findViewById(R.id.picdetail);
                picdetail.setText("3/"+count);
                bigpiclist.add(view3);

                img4= (PhotoView) view4.findViewById(R.id.BigImg);
                img4.enable();
                img4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img4.setOnLongClickListener(this);
                Glide.with(this).load(piclist[3]).into(img4);
                picdetail=(TextView)view4.findViewById(R.id.picdetail);
                picdetail.setText("4/"+count);
                bigpiclist.add(view4);

                img5= (PhotoView) view5.findViewById(R.id.BigImg);
                img5.enable();
                img5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img5.setOnLongClickListener(this);
                Glide.with(this).load(piclist[4]).into(img5);
                picdetail=(TextView)view5.findViewById(R.id.picdetail);
                picdetail.setText("5/"+count);
                bigpiclist.add(view5);


                viewPager.setAdapter(new MyAdapter());
                viewPager.setCurrentItem(position);
                break;
            }
            case 6:
            {
                img1= (PhotoView) view1.findViewById(R.id.BigImg);
                img1.enable();
                img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img1.setOnLongClickListener(this);
                Glide.with(this).load(piclist[0]).into(img1);
                picdetail=(TextView)view1.findViewById(R.id.picdetail);
                picdetail.setText("1/"+count);
                bigpiclist.add(view1);

                img2= (PhotoView) view2.findViewById(R.id.BigImg);
                img2.enable();
                img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img2.setOnLongClickListener(this);
                Glide.with(this).load(piclist[1]).into(img2);
                picdetail=(TextView)view2.findViewById(R.id.picdetail);
                picdetail.setText("2/"+count);
                bigpiclist.add(view2);

                img3= (PhotoView) view3.findViewById(R.id.BigImg);
                img3.enable();
                img3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img3.setOnLongClickListener(this);
                Glide.with(this).load(piclist[2]).into(img3);
                picdetail=(TextView)view3.findViewById(R.id.picdetail);
                picdetail.setText("3/"+count);
                bigpiclist.add(view3);

                img4= (PhotoView) view4.findViewById(R.id.BigImg);
                img4.enable();
                img4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img4.setOnLongClickListener(this);
                Glide.with(this).load(piclist[3]).into(img4);
                picdetail=(TextView)view4.findViewById(R.id.picdetail);
                picdetail.setText("4/"+count);
                bigpiclist.add(view4);

                img5= (PhotoView) view5.findViewById(R.id.BigImg);
                img5.enable();
                img5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img5.setOnLongClickListener(this);
                Glide.with(this).load(piclist[4]).into(img5);
                picdetail=(TextView)view5.findViewById(R.id.picdetail);
                picdetail.setText("5/"+count);
                bigpiclist.add(view5);

                img6= (PhotoView) view6.findViewById(R.id.BigImg);
                img6.enable();
                img6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img6.setOnLongClickListener(this);
                Glide.with(this).load(piclist[5]).into(img6);
                picdetail=(TextView)view6.findViewById(R.id.picdetail);
                picdetail.setText("6/"+count);
                bigpiclist.add(view6);


                viewPager.setAdapter(new MyAdapter());
                viewPager.setCurrentItem(position);
                break;
            }
            case 7:
            {
                img1= (PhotoView) view1.findViewById(R.id.BigImg);
                img1.enable();
                img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img1.setOnLongClickListener(this);
                Glide.with(this).load(piclist[0]).into(img1);
                picdetail=(TextView)view1.findViewById(R.id.picdetail);
                picdetail.setText("1/"+count);
                bigpiclist.add(view1);

                img2= (PhotoView) view2.findViewById(R.id.BigImg);
                img2.enable();
                img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img2.setOnLongClickListener(this);
                Glide.with(this).load(piclist[1]).into(img2);
                picdetail=(TextView)view2.findViewById(R.id.picdetail);
                picdetail.setText("2/"+count);
                bigpiclist.add(view2);

                img3= (PhotoView) view3.findViewById(R.id.BigImg);
                img3.enable();
                img3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img3.setOnLongClickListener(this);
                Glide.with(this).load(piclist[2]).into(img3);
                picdetail=(TextView)view3.findViewById(R.id.picdetail);
                picdetail.setText("3/"+count);
                bigpiclist.add(view3);

                img4= (PhotoView) view4.findViewById(R.id.BigImg);
                img4.enable();
                img4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img4.setOnLongClickListener(this);
                Glide.with(this).load(piclist[3]).into(img4);
                picdetail=(TextView)view4.findViewById(R.id.picdetail);
                picdetail.setText("4/"+count);
                bigpiclist.add(view4);

                img5= (PhotoView) view5.findViewById(R.id.BigImg);
                img5.enable();
                img5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img5.setOnLongClickListener(this);
                Glide.with(this).load(piclist[4]).into(img5);
                picdetail=(TextView)view5.findViewById(R.id.picdetail);
                picdetail.setText("5/"+count);
                bigpiclist.add(view5);

                img6= (PhotoView) view6.findViewById(R.id.BigImg);
                img6.enable();
                img6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img6.setOnLongClickListener(this);
                Glide.with(this).load(piclist[5]).into(img6);
                picdetail=(TextView)view6.findViewById(R.id.picdetail);
                picdetail.setText("6/"+count);
                bigpiclist.add(view6);

                img7= (PhotoView) view7.findViewById(R.id.BigImg);
                img7.enable();
                img7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img7.setOnLongClickListener(this);
                Glide.with(this).load(piclist[6]).into(img7);
                picdetail=(TextView)view7.findViewById(R.id.picdetail);
                picdetail.setText("7/"+count);
                bigpiclist.add(view7);


                viewPager.setAdapter(new MyAdapter());
                viewPager.setCurrentItem(position);
                break;
            }
            case 8:
            {
                img1= (PhotoView) view1.findViewById(R.id.BigImg);
                img1.enable();
                img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img1.setOnLongClickListener(this);
                Glide.with(this).load(piclist[0]).into(img1);
                picdetail=(TextView)view1.findViewById(R.id.picdetail);
                picdetail.setText("1/"+count);
                bigpiclist.add(view1);

                img2= (PhotoView) view2.findViewById(R.id.BigImg);
                img2.enable();
                img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img2.setOnLongClickListener(this);
                Glide.with(this).load(piclist[1]).into(img2);
                picdetail=(TextView)view2.findViewById(R.id.picdetail);
                picdetail.setText("2/"+count);
                bigpiclist.add(view2);

                img3= (PhotoView) view3.findViewById(R.id.BigImg);
                img3.enable();
                img3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img3.setOnLongClickListener(this);
                Glide.with(this).load(piclist[2]).into(img3);
                picdetail=(TextView)view3.findViewById(R.id.picdetail);
                picdetail.setText("3/"+count);
                bigpiclist.add(view3);

                img4= (PhotoView) view4.findViewById(R.id.BigImg);
                img4.enable();
                img4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img4.setOnLongClickListener(this);
                Glide.with(this).load(piclist[3]).into(img4);
                picdetail=(TextView)view4.findViewById(R.id.picdetail);
                picdetail.setText("4/"+count);
                bigpiclist.add(view4);

                img5= (PhotoView) view5.findViewById(R.id.BigImg);
                img5.enable();
                img5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img5.setOnLongClickListener(this);
                Glide.with(this).load(piclist[4]).into(img5);
                picdetail=(TextView)view5.findViewById(R.id.picdetail);
                picdetail.setText("5/"+count);
                bigpiclist.add(view5);

                img6= (PhotoView) view6.findViewById(R.id.BigImg);
                img6.enable();
                img6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img6.setOnLongClickListener(this);
                Glide.with(this).load(piclist[5]).into(img6);
                picdetail=(TextView)view6.findViewById(R.id.picdetail);
                picdetail.setText("6/"+count);
                bigpiclist.add(view6);

                img7= (PhotoView) view7.findViewById(R.id.BigImg);
                img7.enable();
                img7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img7.setOnLongClickListener(this);
                Glide.with(this).load(piclist[6]).into(img7);
                picdetail=(TextView)view7.findViewById(R.id.picdetail);
                picdetail.setText("7/"+count);
                bigpiclist.add(view7);

                img8= (PhotoView) view8.findViewById(R.id.BigImg);
                img8.enable();
                img8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img8.setOnLongClickListener(this);
                Glide.with(this).load(piclist[7]).into(img8);
                picdetail=(TextView)view8.findViewById(R.id.picdetail);
                picdetail.setText("8/"+count);
                bigpiclist.add(view8);


                viewPager.setAdapter(new MyAdapter());
                viewPager.setCurrentItem(position);
                break;
            }
            case 9:
            {
                img1= (PhotoView) view1.findViewById(R.id.BigImg);
                img1.enable();
                img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img1.setOnLongClickListener(this);
                Glide.with(this).load(piclist[0]).into(img1);
                picdetail=(TextView)view1.findViewById(R.id.picdetail);
                picdetail.setText("1/"+count);
                bigpiclist.add(view1);

                img2= (PhotoView) view2.findViewById(R.id.BigImg);
                img2.enable();
                img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img2.setOnLongClickListener(this);
                Glide.with(this).load(piclist[1]).into(img2);
                picdetail=(TextView)view2.findViewById(R.id.picdetail);
                picdetail.setText("2/"+count);
                bigpiclist.add(view2);

                img3= (PhotoView) view3.findViewById(R.id.BigImg);
                img3.enable();
                img3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img3.setOnLongClickListener(this);
                Glide.with(this).load(piclist[2]).into(img3);
                picdetail=(TextView)view3.findViewById(R.id.picdetail);
                picdetail.setText("3/"+count);
                bigpiclist.add(view3);

                img4= (PhotoView) view4.findViewById(R.id.BigImg);
                img4.enable();
                img4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img4.setOnLongClickListener(this);
                Glide.with(this).load(piclist[3]).into(img4);
                picdetail=(TextView)view4.findViewById(R.id.picdetail);
                picdetail.setText("4/"+count);
                bigpiclist.add(view4);

                img5= (PhotoView) view5.findViewById(R.id.BigImg);
                img5.enable();
                img5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img5.setOnLongClickListener(this);
                Glide.with(this).load(piclist[4]).into(img5);
                picdetail=(TextView)view5.findViewById(R.id.picdetail);
                picdetail.setText("5/"+count);
                bigpiclist.add(view5);

                img6= (PhotoView) view6.findViewById(R.id.BigImg);
                img6.enable();
                img6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img6.setOnLongClickListener(this);
                Glide.with(this).load(piclist[5]).into(img6);
                picdetail=(TextView)view6.findViewById(R.id.picdetail);
                picdetail.setText("6/"+count);
                bigpiclist.add(view6);

                img7= (PhotoView) view7.findViewById(R.id.BigImg);
                img7.enable();
                img7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img7.setOnLongClickListener(this);
                Glide.with(this).load(piclist[6]).into(img7);
                picdetail=(TextView)view7.findViewById(R.id.picdetail);
                picdetail.setText("7/"+count);
                bigpiclist.add(view7);

                img8= (PhotoView) view8.findViewById(R.id.BigImg);
                img8.enable();
                img8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img8.setOnLongClickListener(this);
                Glide.with(this).load(piclist[7]).into(img8);
                picdetail=(TextView)view8.findViewById(R.id.picdetail);
                picdetail.setText("8/"+count);
                bigpiclist.add(view8);

                img9= (PhotoView) view9.findViewById(R.id.BigImg);
                img9.enable();
                img9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                img9.setOnLongClickListener(this);
                Glide.with(this).load(piclist[8]).into(img9);
                picdetail=(TextView)view9.findViewById(R.id.picdetail);
                picdetail.setText("9/"+count);
                bigpiclist.add(view9);


                Log.d("position", "onCreate: "+position);
                viewPager.setAdapter(new MyAdapter());
                viewPager.setCurrentItem(position);
                break;
            }
            default:
            {
                break;
            }

        }

//        viewPager.setAdapter(new MyAdapter());


    }

    @Override
    public boolean onLongClick(View view) {
        MyBottomDialog dialog=new MyBottomDialog(context);
        dialog.setpicdetail(index,piclist);
        dialog.show();
        return true;
    }


    class MyAdapter extends PagerAdapter
    {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(bigpiclist.get(position));
            return bigpiclist.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(bigpiclist.get(position));

        }


        @Override
        public int getCount() {
            return bigpiclist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }
}
