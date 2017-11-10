package com.five.fashion.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.five.fashion.R;
import com.five.fashion.home.bean.Homebean;

import java.util.List;

/**
 * Created by wangyajie on 2017/10/18.
 */

public class HomeHotImageAdapter extends RecyclerView.Adapter<HomeHotImageAdapter.MyViewHolder> {
    Context context;
    List<Homebean.DataBean.ActivityInfoBean.ActivityInfoListBean> ad5;

    public HomeHotImageAdapter(Context context, List<Homebean.DataBean.ActivityInfoBean.ActivityInfoListBean> ad5) {
        this.context = context;
        this.ad5 = ad5;
    }

    @Override
    public HomeHotImageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.home_item_hot_image, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeHotImageAdapter.MyViewHolder holder, int position) {
        Glide.with(context).load(ad5.get(position).getActivityImg()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return ad5.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);

            imageView = (ImageView) view.findViewById(R.id.home_item_hot_image);
        }
    }
}
