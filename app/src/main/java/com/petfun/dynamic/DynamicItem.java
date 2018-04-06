package com.petfun.dynamic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//用于定义item的数据成员

/**
 * Created by yjc19 on 2017/10/29.
 */

public class DynamicItem implements Serializable{

    private  String qNumber,userName,content,time,userId,dynamicId;
    private int likes,comments;

   private List<String> imageList =new ArrayList<>();

    public String getHeadLink() {
        return qNumber;
    }//发表者头像

    public String getUserId() {
        return userId;
    }

    public String getDynamicId() {
        return dynamicId;
    }

    public String getqNumber() {
        return qNumber;
    }

    public String getUserName() {
        return userName;
    }//发表者名字

    public String getContent() {
        return content;
    }//内容

    public String getTime() {
        return time;
    }//发布时间

    public int getLikes() {
        return likes;
    }//点赞数

    public int getComments() {
        return comments;
    }//评论数

    public List<String> getImageList() {
        return imageList;
    }//图片链接
}
