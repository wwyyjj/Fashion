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
 * Created by wangyajie on 2017/10/23.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    Context context;
    List<Homebean.DataBean.Ad5Bean> ad5;

    public HomeAdapter(Context context, List<Homebean.DataBean.Ad5Bean> ad5) {
        this.context = context;
        this.ad5 = ad5;
    }

    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.home_item_grid_center, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(ad5.get(position).getTitle());
        Glide.with(context).load(ad5.get(position).getImage()).into(holder.imageView);
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
            tv = (TextView) view.findViewById(R.id.home_item_grid_center_textView);
            imageView = (ImageView) view.findViewById(R.id.home_item_grid_center_image);
        }
    }
}
