package com.petfun.dynamicdetail;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.petfun.dynamic.HttpUtil;
import com.petfun.dynamicdetail.DynamicDetails;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Lenovo on 2017/10/29.
 */

public class DynamicDetailData {
    public List<DynamicDetails> dynamicDetailses =new ArrayList<>();
    private String address=null;
    public DynamicDetailData(String commentId){
        address="http://petfun.iprograms.cn/dynamic/json/comment/"+commentId+".json";
        Log.d("Test", "DynamicDetailData: "+address);
    }
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
                Log.d("Test", "onError: GetDataError");
            }
        });
    }
    private void GetDataFromJson(String jsonData)
    {
        Gson gson = new Gson();
//        Log.d("Test", "GetDataFromJson: --------------------\n"+jsonData);
        dynamicDetailses = gson.fromJson(jsonData,new TypeToken<List<DynamicDetails>>()
        {}.getType());
    }

    public List<DynamicDetails> getData()
    {
        try{
            Thread.sleep(1000);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return dynamicDetailses;
    }
}
