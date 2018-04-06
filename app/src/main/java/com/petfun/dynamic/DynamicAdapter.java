package com.petfun.dynamic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.petfun.dynamicdetail.DynamicDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjc19 on 2017/10/29.
 */

public class DynamicAdapter extends RecyclerView.Adapter<DynamicAdapter.ViewHolder> implements View.OnLongClickListener {
    int flag=0;
    private List<DynamicItem>list;
    private Context context;
    int imagecount;
    public void setlist(List<DynamicItem>list){this.list=list;}

    public DynamicAdapter(Context context)
    {
        this.list=new ArrayList<>();
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context==null)
        {
            context=parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.dynamic_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);

        holder.dynamicview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Intent intent=new Intent(context,DynamicDetailActivity.class);
                DynamicItem itemdata=list.get(position);
                intent.putExtra("Dynamicdata",itemdata);
                context.startActivity(intent);
            }
        });

        holder.image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position=holder.getAdapterPosition();
                Intent intent=new Intent(context,ShowBigPicActivity.class);
                DynamicItem itemdata=list.get(position);
                String[] piclist= itemdata.getImageList().toArray(new String[0]);
               intent.putExtra("piclist",piclist);
                int index=0;
                intent.putExtra("position",index);
                context.startActivity(intent);
            }
        });
        holder.image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position=holder.getAdapterPosition();
                Intent intent=new Intent(context,ShowBigPicActivity.class);
                DynamicItem itemdata=list.get(position);
                String[] piclist= itemdata.getImageList().toArray(new String[0]);
                intent.putExtra("piclist",piclist);
                int index=1;
                intent.putExtra("position",index);

                context.startActivity(intent);
            }
        });
        holder.image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position=holder.getAdapterPosition();
                Intent intent=new Intent(context,ShowBigPicActivity.class);
                DynamicItem itemdata=list.get(position);
                String[] piclist= itemdata.getImageList().toArray(new String[0]);
                intent.putExtra("piclist",piclist);
                int index=2;
                intent.putExtra("position",index);

                context.startActivity(intent);
            }
        });
        holder.image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position=holder.getAdapterPosition();
                Intent intent=new Intent(context,ShowBigPicActivity.class);
                DynamicItem itemdata=list.get(position);
                String[] piclist= itemdata.getImageList().toArray(new String[0]);
                intent.putExtra("piclist",piclist);
                int index=3;
                intent.putExtra("position",index);

                context.startActivity(intent);
            }
        });
        holder.image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position=holder.getAdapterPosition();
                Intent intent=new Intent(context,ShowBigPicActivity.class);
                DynamicItem itemdata=list.get(position);
                String[] piclist= itemdata.getImageList().toArray(new String[0]);
                intent.putExtra("piclist",piclist);
                int index=4;
                intent.putExtra("position",index);

                context.startActivity(intent);
            }
        });
        holder.image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position=holder.getAdapterPosition();
                Intent intent=new Intent(context,ShowBigPicActivity.class);
                DynamicItem itemdata=list.get(position);
                String[] piclist= itemdata.getImageList().toArray(new String[0]);
                intent.putExtra("piclist",piclist);
                int index=5;
                intent.putExtra("position",index);

                context.startActivity(intent);
            }
        });
        holder.image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position=holder.getAdapterPosition();
                Intent intent=new Intent(context,ShowBigPicActivity.class);
                DynamicItem itemdata=list.get(position);
                String[] piclist= itemdata.getImageList().toArray(new String[0]);
                intent.putExtra("piclist",piclist);
                int index=6;
                intent.putExtra("position",index);

                context.startActivity(intent);
            }
        });
        holder.image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position=holder.getAdapterPosition();
                Intent intent=new Intent(context,ShowBigPicActivity.class);
                DynamicItem itemdata=list.get(position);
                String[] piclist= itemdata.getImageList().toArray(new String[0]);
                intent.putExtra("piclist",piclist);
                int index=7;
                intent.putExtra("position",index);

                context.startActivity(intent);
            }
        });
        holder.image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position=holder.getAdapterPosition();
                Intent intent=new Intent(context,ShowBigPicActivity.class);
                DynamicItem itemdata=list.get(position);
                String[] piclist= itemdata.getImageList().toArray(new String[0]);
                intent.putExtra("piclist",piclist);
                int index=8;
                intent.putExtra("position",index);

                context.startActivity(intent);
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final DynamicItem item=list.get(position);
        Log.d("link", "onBindViewHolder: "+item.getHeadLink());
        Glide.with(context).load(item.getHeadLink()).into(holder.user_image);
        holder.user_name.setText(item.getUserName());
        holder.content.setText(item.getContent());
        holder.date.setText(item.getTime());
        holder.dianzan_count.setText(item.getLikes()+"人点赞");
        holder.comment_count.setText(item.getComments()+"条评论");
        List<String> imagelist=item.getImageList();
        Log.d("test", "numbers of img: "+imagelist.size());

        if(imagelist==null)
        {
            imagecount=0;
        }
        else
        {
            imagecount=imagelist.size();
        }

        Log.d("IMAGECOUNT", "onBindViewHolder: "+"----------"+imagecount);
        switch (imagecount)
        {
            case 0:
            {
                holder.image1.setVisibility(View.GONE);
                holder.image2.setVisibility(View.GONE);
                holder.image3.setVisibility(View.GONE);
                holder.image4.setVisibility(View.GONE);
                holder.image5.setVisibility(View.GONE);
                holder.image6.setVisibility(View.GONE);
                holder.image7.setVisibility(View.GONE);
                holder.image8.setVisibility(View.GONE);
                holder.image9.setVisibility(View.GONE);
            }

            break;
            case 1:
            {
                holder.image1.setVisibility(View.VISIBLE);
                holder.image2.setVisibility(View.INVISIBLE);
                holder.image3.setVisibility(View.INVISIBLE);
                holder.image_row2.setVisibility(View.GONE);
                holder.image_row3.setVisibility(View.GONE);
                Glide.with(context).load(imagelist.get(0)).into(holder.image1);
            }
            break;
            case 2:
            {
                holder.image1.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(0)).into(holder.image1);
                holder.image2.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(1)).into(holder.image2);
                holder.image3.setVisibility(View.INVISIBLE);
                holder.image_row2.setVisibility(View.GONE);
                holder.image_row3.setVisibility(View.GONE);
            }
            break;
            case 3:
            {
                holder.image1.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(0)).into(holder.image1);
                holder.image2.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(1)).into(holder.image2);
                holder.image3.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(2)).into(holder.image3);
                holder.image_row2.setVisibility(View.GONE);
                holder.image_row3.setVisibility(View.GONE);
            }
            break;
            case 4:
            {
                holder.image1.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(0)).into(holder.image1);
                holder.image2.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(1)).into(holder.image2);
                holder.image3.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(2)).into(holder.image3);
                holder.image4.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(3)).into(holder.image4);
                holder.image5.setVisibility(View.INVISIBLE);
                holder.image6.setVisibility(View.INVISIBLE);
                holder.image_row3.setVisibility(View.GONE);
            }
            break;
            case 5:
            {
                Log.d("test", "case numbers of img: "+imagelist);

                holder.image1.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(0)).into(holder.image1);
                holder.image2.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(1)).into(holder.image2);
                holder.image3.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(2)).into(holder.image3);
                holder.image4.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(3)).into(holder.image4);
                holder.image5.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(4)).into(holder.image5);
                holder.image6.setVisibility(View.INVISIBLE);
                holder.image_row3.setVisibility(View.GONE);
            }
            break;
            case 6:
            {
                holder.image1.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(0)).into(holder.image1);
                holder.image2.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(1)).into(holder.image2);
                holder.image3.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(2)).into(holder.image3);
                holder.image4.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(3)).into(holder.image4);
                holder.image5.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(4)).into(holder.image5);
                holder.image6.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(5)).into(holder.image6);
                holder.image_row3.setVisibility(View.GONE);
            }
            break;
            case 7:
            {
                holder.image1.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(0)).into(holder.image1);
                holder.image2.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(1)).into(holder.image2);
                holder.image3.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(2)).into(holder.image3);
                holder.image4.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(3)).into(holder.image4);
                holder.image5.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(4)).into(holder.image5);
                holder.image6.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(5)).into(holder.image6);
                holder.image7.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(6)).into(holder.image7);
                holder.image_row2.setVisibility(View.VISIBLE);
                holder.image_row3.setVisibility(View.VISIBLE);
                holder.image8.setVisibility(View.INVISIBLE);
                holder.image9.setVisibility(View.INVISIBLE);

            }
            break;
            case 8:
            {
                holder.image1.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(0)).into(holder.image1);
                holder.image2.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(1)).into(holder.image2);
                holder.image3.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(2)).into(holder.image3);
                holder.image4.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(3)).into(holder.image4);
                holder.image5.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(4)).into(holder.image5);
                holder.image6.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(5)).into(holder.image6);
                holder.image7.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(6)).into(holder.image7);
                holder.image8.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(7)).into(holder.image8);
                holder.image9.setVisibility(View.INVISIBLE);
                holder.image_row2.setVisibility(View.VISIBLE);
                holder.image_row3.setVisibility(View.VISIBLE);
            }
            break;
            case 9:
            {
                holder.image1.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(0)).into(holder.image1);
                holder.image2.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(1)).into(holder.image2);
                holder.image3.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(2)).into(holder.image3);
                holder.image4.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(3)).into(holder.image4);
                holder.image5.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(4)).into(holder.image5);
                holder.image6.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(5)).into(holder.image6);
                holder.image7.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(6)).into(holder.image7);
                holder.image8.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(7)).into(holder.image8);
                holder.image9.setVisibility(View.VISIBLE);
                Glide.with(context).load(imagelist.get(8)).into(holder.image9);
                holder.image_row2.setVisibility(View.VISIBLE);
                holder.image_row3.setVisibility(View.VISIBLE);
            }
            break;
            default:
                break;
        }

        holder.dianzan_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(flag)
                {
                    case 0:
                    {
                        int count=item.getLikes()+1;
                        holder.dianzan_image.setImageResource(R.drawable.love_next);
                        holder.dianzan_count.setText(count+"人点赞");
                        flag=1;
                    }
                    break;
                    case 1:
                    {
                        holder.dianzan_image.setImageResource(R.drawable.love_pre);
                        holder.dianzan_count.setText(item.getLikes()+"人点赞");
                        flag=0;;
                    }
                    break;
                }
            }
        });
        holder.dianzan_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(flag)
                {
                    case 0:
                    {

                        int count=item.getLikes()+1;
                        holder.dianzan_image.setImageResource(R.drawable.love_next);
                        holder.dianzan_count.setText(count+"人点赞");
                        flag=1;
                    }
                    break;
                    case 1:
                    {
                        holder.dianzan_image.setImageResource(R.drawable.love_pre);
                        holder.dianzan_count.setText(item.getLikes()+"人点赞");
                        flag=0;
                    }
                    break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public boolean onLongClick(View view) {
        MyBottomDialog dialog=new MyBottomDialog(context);
        dialog.show();
        return true;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View dynamicview;
        ImageView user_image;
        ImageView image1,image2,image3,image4,image5,image6,image7,image8,image9;
        TextView user_name,content,date,dianzan_count,comment_count;
        ImageButton dianzan_image,comment_image;
        LinearLayout image_row1,image_row2,image_row3;
        public ViewHolder(View itemView) {
            super(itemView);
            dynamicview=itemView;
            user_image=(ImageView)itemView.findViewById(R.id.user_image);
            image_row1=(LinearLayout)itemView.findViewById(R.id.image_row1);
            image_row2=(LinearLayout)itemView.findViewById(R.id.image_row2);
            image_row3=(LinearLayout)itemView.findViewById(R.id.image_row3);
            image1=(ImageView) itemView.findViewById(R.id.image1);
            image2=(ImageView)itemView.findViewById(R.id.image2);
            image3=(ImageView)itemView.findViewById(R.id.image3);
            image4=(ImageView)itemView.findViewById(R.id.image4);
            image5=(ImageView)itemView.findViewById(R.id.image5);
            image6=(ImageView)itemView.findViewById(R.id.image6);
            image7=(ImageView)itemView.findViewById(R.id.image7);
            image8=(ImageView)itemView.findViewById(R.id.image8);
            image9=(ImageView)itemView.findViewById(R.id.image9);
            user_name=(TextView)itemView.findViewById(R.id.user_name);
            content=(TextView)itemView.findViewById(R.id.content);
            date=(TextView)itemView.findViewById(R.id.date);
            dianzan_count=(TextView)itemView.findViewById(R.id.dianzan_count);
            comment_count=(TextView)itemView.findViewById(R.id.comment_count);
            dianzan_image=(ImageButton)itemView.findViewById(R.id.dianzan_image);
            comment_image=(ImageButton)itemView.findViewById(R.id.comment_image);

        }
    }


}
