package com.five.fashion.sort.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.five.fashion.R;
import com.five.fashion.sort.bean.SongListbean;

import java.util.List;

/**
 * Created by wangyajie on 2017/11/17.
 */

public class ListViewAdapter extends BaseAdapter{
    Context context;
    List<SongListbean.DataBean> data;

    public ListViewAdapter(Context context, List<SongListbean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.sort_item_listview,null);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.sort_item_list_text);
            holder.img = (ImageView) convertView.findViewById(R.id.sort_item_list_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text.setText(data.get(position).getTitle());
        String s = data.get(position).getImages().split("\\|")[0];
        Glide.with(context).load(s).placeholder(R.mipmap.ic_launcher).into(holder.img);
        return convertView;
    }
    static class ViewHolder {
        TextView text;
        ImageView img;
    }
}
