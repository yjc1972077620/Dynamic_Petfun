package com.petfun.dynamicgallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.petfun.dynamic.MyBottomDialog;
import com.petfun.dynamic.R;

public class ShowGalleryPicture extends AppCompatActivity implements View.OnLongClickListener  {

    private PhotoView gallery_img;
    private  String image_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_gallery_picture);
        gallery_img=(PhotoView)findViewById(R.id.gallery_img);
        Intent intent=getIntent();
        image_url=intent.getStringExtra("gallery");
        Glide.with(this).load(image_url).into(gallery_img);
        gallery_img.enable();
        gallery_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        gallery_img.setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View view) {
        MyBottomDialog dialog=new MyBottomDialog(this);
        String[] piclist=new String[1];
        piclist[0]=image_url;
        dialog.setpicdetail(0,piclist);
        dialog.show();
        return true;
    }
}
