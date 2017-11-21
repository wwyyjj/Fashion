package com.five.fashion.sort.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.five.fashion.R;
import com.five.fashion.sort.bean.SortTwobean;
import com.five.fashion.sort.show.ShowListActivity;
import com.five.fashion.sort.show.Showlistbean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * description: $todo$
 * autour: BlueAmer
 * date: $date$ $time$
 * update: $date$
 * version: $version$
 */

public class MyAdapter_TypeGridView extends BaseAdapter {
    private Context context;
    private List<SortTwobean.DataBean.ListBean> list;
    public static final String TAG = "MyAdapter_TypeGridView";

    public MyAdapter_TypeGridView(Context context, List<SortTwobean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.type_grid_item, null);
            holder = new ViewHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.tv_gv_type);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(list.get(position).getName());
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new Showlistbean(list.get(position).getPscid()));
                Intent intent = new Intent(context, ShowListActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView tv;
    }
}
