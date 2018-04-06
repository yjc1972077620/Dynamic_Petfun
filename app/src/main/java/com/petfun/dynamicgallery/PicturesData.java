package com.petfun.dynamicgallery;


import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.petfun.dynamic.HttpUtil;
import com.petfun.dynamicgallery.Pictures;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Lenovo on 2017/10/29.
 */

public class  PicturesData{
    public List<Pictures> pictures =new ArrayList<>();
    private String address = "http://petfun.iprograms.cn/dynamic/json/pictures.json";
    public void initList()
    {
        HttpUtil.sendOkHttpRequest(address,new okhttp3.Callback(){
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                GetDataFromJson(jsonData);
            }
            @Override
            public void onFailure(Call call,IOException e){
//                Log.d("Test", "onError: GetDataError");
            }
        });
    }
    private void GetDataFromJson(String jsonData)
    {
        Gson gson = new Gson();
//        Log.d("Test", "GetDataFromJson: --------------------\n"+jsonData);
        pictures = gson.fromJson(jsonData,new TypeToken<List<Pictures>>()
        {}.getType());
    }

    public List<Pictures> getData()
    {
        try{
            Thread.sleep(2000);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return pictures;
    }
}
