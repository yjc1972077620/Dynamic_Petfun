package com.petfun.dynamic;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.petfun.dynamic.addpic.AddPictureActivity;
import com.petfun.dynamicgallery.DynamicPictureAdapter;
import com.petfun.dynamicgallery.Pictures;
import com.petfun.dynamicgallery.PicturesData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.iprograms.petfun.client.Client;

public class DynamicFragment extends Fragment {
    View view;
    private RecyclerView dynamic_pic_recyclerview;
    private ViewPager dynamic_viewpager;
    private DynamicPictureAdapter picture_adapter;
    private View dynamic_view1,dynamic_view2;
    private List<View> viewList=new ArrayList<>();
    private RecyclerView recyclerView;
    private List<DynamicItem> dynamicItemList =new ArrayList<>();
    private SwipeRefreshLayout dynamic_refresh,gallrey_refresh;
    private DynamicMainData dynamicMainData;
    private ImageView add;
    private DynamicAdapter dynamicAdapter;
    private TextView dynamic;
    private TextView picture;
    private ImageView move_flag;
    private  StaggeredGridLayoutManager staggeredGridLayoutManager;
    private  List<Pictures> pictureItemList=new ArrayList<>();

    private  PagerAdapter pagerAdapter;
    private PicturesData picturesData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=getActivity().getLayoutInflater().inflate(R.layout.activity_dynamic,null);


        Client.initClient();



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        //初始化两个view（动态）/（图库）
        LayoutInflater myinflater=getActivity().getLayoutInflater();
        dynamic_view1=myinflater.inflate(R.layout.dynamic_view1,null);
        dynamic_view2=myinflater.inflate(R.layout.dynamic_view2,null);

        add=(ImageView)view.findViewById(R.id.add);
        recyclerView=(RecyclerView)dynamic_view1.findViewById(R.id.recyclerview);
        dynamic_refresh=(SwipeRefreshLayout)dynamic_view1.findViewById(R.id.dynamic_refresh);
        dynamic_refresh=(SwipeRefreshLayout)dynamic_view1.findViewById(R.id.dynamic_refresh);
        dynamic=(TextView)view.findViewById(R.id.item_dynamic);
        picture=(TextView)view.findViewById(R.id.item_picture);
        move_flag=(ImageView)view.findViewById(R.id.move_flag);
        dynamic_viewpager=(ViewPager)view.findViewById(R.id.dynamic_viewPager);
        dynamic_pic_recyclerview=(RecyclerView)dynamic_view2.findViewById(R.id.picture_recyclerview);
        staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        dynamic_pic_recyclerview.setLayoutManager(staggeredGridLayoutManager);

        //动态和图库的适配器
        picture_adapter=new DynamicPictureAdapter(getActivity());
        dynamicAdapter =new DynamicAdapter(getActivity());

        viewList.add(dynamic_view1);
        viewList.add(dynamic_view2);


        dynamic_refresh.setRefreshing(true);


        new Thread(new Runnable() {
            @Override
            public void run() {

                //从json获取动态数据
                dynamicMainData=new DynamicMainData();
                dynamicMainData.initList();
                dynamicItemList =dynamicMainData.getData();
                Collections.reverse(dynamicItemList);
                dynamicAdapter.setlist(dynamicItemList);

                //从json获取图库数据
                picturesData=new PicturesData();
                picturesData.initList();
                pictureItemList=picturesData.getData();
                picture_adapter.setList(pictureItemList);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        refresh();

                    }
                });
            }
        }).start();

        //配置关于dynamic主界面viewpager的适配器
        pagerAdapter=new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }


            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
        };
        dynamic_viewpager.setAdapter(pagerAdapter);

        //动态toolbar的导航切换实现方法
        final int[] flag = {0};
        final boolean[] isleft = {false};
        dynamic_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if(position==0)
                {
                    flag[0]=0;
                    dynamic.setTextColor(Color.WHITE);
                    picture.setTextColor(Color.BLACK);
                }
                if(position==1)
                {
                    flag[0]=1;
                    dynamic.setTextColor(Color.BLACK);
                    picture.setTextColor(Color.WHITE);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(0<positionOffset&&positionOffset<0.5)
                {
                    isleft[0]=false;
                }
                else if(1>positionOffset&&positionOffset>0.5)
                {
                    isleft[0]=true;
                }

                switch(flag[0]) {
                    case 0: {
                        if (isleft[0]) {
                            flag[0] = 1;
                            final TranslateAnimation animation = new TranslateAnimation(0, 190, 0, 0);
                            if (flag[0] == 1) {
                                animation.setFillAfter(true);
                            }


                            animation.setDuration(200);
                            move_flag.startAnimation(animation);
                        }

                        break;
                    }
                    case 1: {
                        if (!isleft[0]) {
                            flag[0] = 0;
                            final TranslateAnimation animation = new TranslateAnimation(190, 0, 0, 0);
                            if (flag[0] == 0) {
                                animation.setFillAfter(true);
                            }

                            animation.setDuration(200);
                            move_flag.startAnimation(animation);
                        }

                        break;
                    }
                }

            }


            @Override
            public void onPageScrollStateChanged(int state) {



            }
        });
        dynamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag[0]==1)
                {
                    dynamic_viewpager.setCurrentItem(0);
                    dynamic.setTextColor(Color.WHITE);
                    picture.setTextColor(Color.BLACK);
                    final TranslateAnimation animation=new TranslateAnimation(190,0,0,0);
                    animation.setFillAfter(true);
                    animation.setDuration(500);
                    move_flag.startAnimation(animation);
                    flag[0] =0;
                }

            }
        });




        //发表动态的点击事件
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),AddPictureActivity.class);
                startActivity(intent);
            }
        });

        //图片的查看点击事件
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag[0]==0)
                {
                    dynamic_viewpager.setCurrentItem(1);
                    picture.setTextColor(Color.WHITE);
                    dynamic.setTextColor(Color.BLACK);
                    final TranslateAnimation animation=new TranslateAnimation(0,190,0,0);
                    animation.setFillAfter(true);
                    animation.setDuration(500);
                    move_flag.startAnimation(animation);
                    flag[0] =1;
                }
            }
        });




        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());

        //动态列表recycleview的相关配置
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(dynamicAdapter);
        dynamic_pic_recyclerview.setAdapter(picture_adapter);
        //刷新事件方法的重写
        dynamic_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                Toast.makeText(getApplicationContext(),"正在刷新",Toast.LENGTH_SHORT).show();
                refresh();
                Toast.makeText(getActivity().getApplicationContext(),"刷新完毕",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }






//自定义的刷新方法
   public void refresh()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dynamicMainData.initList();
                    picturesData.initList();
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        dynamicItemList =dynamicMainData.getData();
                        Collections.reverse(dynamicItemList);
                        dynamicAdapter.setlist(dynamicItemList);
                        dynamicAdapter.notifyDataSetChanged();

                        pictureItemList=picturesData.getData();
                        picture_adapter.setList(pictureItemList);
                        picture_adapter.notifyDataSetChanged();

                        dynamic_refresh.setRefreshing(false);
                        recyclerView.smoothScrollToPosition(0);

                    }
                });
            }
        }).start();
    }
}
