package com.petfun.dynamic;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

//这个类是获取网络数据的

/**
 * Created by Lenovo on 2017/10/28.
 */

public class DynamicMainData {
    public List<DynamicItem> dynamicMains =new ArrayList<>();
    String address = "http://petfun.iprograms.cn/dynamic/json/dynamic.json";
    void initList()
    {
        HttpUtil.sendOkHttpRequest(address,new okhttp3.Callback(){
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                GetDataFromJson(jsonData);
            }
            @Override
            public void onFailure(Call call,IOException e){
                Log.d("test", "onError: GetDataError");
            }
        });
    }
    private void GetDataFromJson(String jsonData)
    {
        Gson gson = new Gson();
        dynamicMains = gson.fromJson(jsonData,new TypeToken<List<DynamicItem>>()
        {}.getType());
        Log.d("test", "GetDataFromJson: "+dynamicMains.size());
    }
    public List<DynamicItem> getData()
    {
        try{
            Thread.sleep(1000);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return dynamicMains;
    }
}
