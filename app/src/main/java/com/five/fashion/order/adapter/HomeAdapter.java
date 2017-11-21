package com.five.fashion.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.five.fashion.R;
import com.five.fashion.order.bean.orderListBean;

import java.util.List;


/**
 * Created by wangyajie on 2017/10/23.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> implements View.OnClickListener {
    Context context;
    List<orderListBean.DataBean> data;

    public HomeAdapter(Context context, List<orderListBean.DataBean> data) {
        this.context = context;
        this.data = data;
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
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.order_item_recy, parent, false);
        MyViewHolder holder = new MyViewHolder(inflate);
        inflate.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       holder.orderCreatetime.setText("此订单创建时间是："+data.get(position).getCreatetime());
       holder.orderOrderid.setText("此订单订单号是："+data.get(position).getOrderid());
       holder.orderPrice.setText("此订单总价是："+data.get(position).getPrice()+"");holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView orderCreatetime;
        TextView orderPrice;
        TextView orderOrderid;

        public MyViewHolder(View itemView) {
            super(itemView);
            orderPrice=itemView.findViewById(R.id.order_price);
            orderCreatetime=itemView.findViewById(R.id.order_createtime);
            orderOrderid=itemView.findViewById(R.id.order_orderid);
        }
    }
}
