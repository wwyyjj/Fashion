package com.five.fashion.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.five.fashion.R;
import com.five.fashion.home.bean.Homebean;

import java.util.List;

/**
 * Created by wangyajie on 2017/10/18.
 */

public class HomeloveAdapter extends RecyclerView.Adapter<HomeloveAdapter.MyViewHolder> {
    Context context;
    List<Homebean.DataBean.SubjectsBean.GoodsRelationListBean> ad5;

    public HomeloveAdapter(Context context, List<Homebean.DataBean.SubjectsBean.GoodsRelationListBean> ad5) {
        this.context = context;
        this.ad5 = ad5;
    }

    @Override
    public HomeloveAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.home_item_love, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeloveAdapter.MyViewHolder holder, int position) {
        holder.tv.setText(ad5.get(position).getDescription());
        Glide.with(context).load(ad5.get(position).getGoodsImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return ad5.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.home_item_love_textView);
            imageView = (ImageView) view.findViewById(R.id.home_item_love_image);
        }
    }
}
