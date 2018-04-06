package com.petfun.dynamicdetail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.petfun.dynamic.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yjc19 on 2017/10/29.
 */

public class CommentAdapter extends BaseAdapter {
    private List<DynamicDetails>list=new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    public CommentAdapter(Context context)
    {
        this.context=context;

        inflater=LayoutInflater.from(context);
    }

    public void setlist(List<DynamicDetails> list)
    {
        this.list=list;
    }
    @Override
    public int getCount() {
        if(list==null||list.size()==0)
        {
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView==null)
        {
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.comment_item,null);
            viewHolder.commenter_image=(CircleImageView) convertView.findViewById(R.id.comment_image);
            viewHolder.commenter_name=(TextView)convertView.findViewById(R.id.commenter_name);
            viewHolder.comment_content=(TextView)convertView.findViewById(R.id.comment_content);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        DynamicDetails item=list.get(position);
        Log.d("Test", "getView: ---------------------------------"+item.getCommentHeadLink());
        Glide.with(context).load(item.getCommentHeadLink()).into(viewHolder.commenter_image);
        viewHolder.commenter_name.setText(item.getCommentUserName());
        viewHolder.comment_content.setText(item.getContent());

        return convertView;
    }
    class ViewHolder
    {
        CircleImageView commenter_image;
        TextView commenter_name,comment_content,click_test;
    }
}
