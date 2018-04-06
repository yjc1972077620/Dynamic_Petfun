package com.petfun.dynamicgallery;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.petfun.dynamic.R;

import java.util.ArrayList;
import java.util.List;



public class DynamicPictureAdapter extends  RecyclerView.Adapter<DynamicPictureAdapter.ViewHolder>  {
    private List<Pictures> dynamicPictureList;
    private Context context;
    private int flag=0;
    public void setList(List<Pictures> list)
    {
        this.dynamicPictureList=list;
    }

    public DynamicPictureAdapter(Context context)
    {
        this.dynamicPictureList=new ArrayList<>();
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context==null)
        {
            context=parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.dynamic_picture_item,parent,false);

        final ViewHolder holder=new ViewHolder(view);
        holder.dynamic_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                Intent intent=new Intent(context,ShowGalleryPicture.class);
                intent.putExtra("gallery",dynamicPictureList.get(position).getImage());
                context.startActivity(intent);
            }
        });

        holder.dianzan_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();

                switch(flag)
                {
                    case 0:
                    {
                        int count=dynamicPictureList.get(position).getLikes()+1;
                        holder.dianzan_pic.setImageResource(R.drawable.love_next);
                        holder.dianzan_count.setText(String.valueOf(count));
                        flag=1;
                    }
                    break;
                    case 1:
                    {
                        holder.dianzan_pic.setImageResource(R.drawable.love_pre);
                        holder.dianzan_count.setText(String.valueOf(dynamicPictureList.get(position).getLikes()));
                        flag=0;;
                    }
                    break;
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(dynamicPictureList.get(position).getImage()).into(holder.dynamic_picture);
        Glide.with(context).load(dynamicPictureList.get(position).getUserImage()).into(holder.poster_headpicture);
        holder.poster_name.setText(dynamicPictureList.get(position).getUserName());
        holder.post_date.setText(dynamicPictureList.get(position).getDate());
        String dianzan_count= String.valueOf(dynamicPictureList.get(position).getLikes());
        holder.dianzan_count.setText(dianzan_count);

    }

    @Override
    public int getItemCount() {
        return dynamicPictureList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView dynamic_picture;
        private ImageView poster_headpicture;
        private ImageView dianzan_pic;
        private TextView poster_name;
        private TextView post_date;
        private TextView dianzan_count;
        public ViewHolder(View itemView) {
            super(itemView);
            dianzan_pic=(ImageView)itemView.findViewById(R.id.gallery_dianzan_img);
            dynamic_picture=(ImageView) itemView.findViewById(R.id.dynamic_picture);
            poster_headpicture=(ImageView)itemView.findViewById(R.id.poster_headpicture);
            poster_name=(TextView)itemView.findViewById(R.id.poster_name);
            post_date=(TextView)itemView.findViewById(R.id.post_date);
            dianzan_count=(TextView)itemView.findViewById(R.id.picture_dianzan_count);
        }
    }
}
