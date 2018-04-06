package com.petfun.dynamicgallery;

import android.widget.ImageView;
import android.widget.TextView;



public class DynamicPictureItem {

    private String picture;
    private String poster_name;
    private String post_date;
    private int dianzan_count;
    private String poster_headpicture;

    public int getDianzan_count() {
        return dianzan_count;
    }

    public String getPicture() {
        return picture;
    }

    public String getPost_date() {
        return post_date;
    }

    public String getPoster_headpicture() {
        return poster_headpicture;
    }

    public String getPoster_name() {
        return poster_name;
    }

    public void setDianzan_count(int dianzan_count) {
        this.dianzan_count = dianzan_count;
    }

    public void setPoster_name(String poster_name) {
        this.poster_name = poster_name;
    }

    public void setPoster_headpicture(String poster_headpicture) {
        this.poster_headpicture = poster_headpicture;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
