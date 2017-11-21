package com.five.fashion.sort.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.five.fashion.R;
import com.five.fashion.sort.bean.SongListbean;

import java.util.List;


/**
 * Created by wangyajie on 2017/10/23.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> implements View.OnClickListener{
    Context context;
    List<SongListbean.DataBean> ad5;

    public HomeAdapter(Context context, List<SongListbean.DataBean> ad5) {
        this.context = context;
        this.ad5 = ad5;
    }
    private OnItemClickListener mOnItemClickListener = null;

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.sort_item_listview, parent, false);
        MyViewHolder holder = new MyViewHolder(inflate);
        inflate.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(ad5.get(position).getTitle());
        String s = ad5.get(position).getImages().split("\\|")[0];
        Glide.with(context).load(s).placeholder(R.mipmap.ic_launcher).into(holder.imageView);
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
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
            tv = (TextView) view.findViewById(R.id.sort_item_list_text);
            imageView = (ImageView) view.findViewById(R.id.sort_item_list_img);
        }
    }
}
