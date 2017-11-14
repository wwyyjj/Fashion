package com.five.fashion.sort.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.five.fashion.R;
import com.five.fashion.sort.bean.DatarightBean;
import com.five.fashion.sort.bean.DateGridBean;
import com.five.fashion.sort.presenter.ThreePresenter;
import com.five.fashion.sort.utils.API;
import com.five.fashion.sort.view.ThreeIView;

import java.util.List;

/**
 * autour: 樊彦龙
 * date: 2017/10/20 13:21
 * update: 2017/10/20
 */

public class MyAdapter_right extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ThreeIView {
    private Context context;
    private List<DatarightBean.DatasBean.ClassListBean> list;
    private ThreePresenter threePresenter;

    public MyAdapter_right(Context context, List<DatarightBean.DatasBean.ClassListBean> list) {
        this.context = context;
        this.list = list;
        this.threePresenter = new ThreePresenter(this);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.typeson_item, parent, false);
        final MyLeftViewHolder leftViewHolder = new MyLeftViewHolder(view);
        return leftViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        threePresenter.initThreeModel(API.TYPE_BODY+ "&gc_id=" + list.get(position).getGc_id());
        //设置种类标题
        final MyLeftViewHolder myHolder = new MyLeftViewHolder(holder.itemView);

        //设置标题
        myHolder.tv_left_type.setText(list.get(position).getGc_name());
        /*//第三次请求网络 获取第三级数据
        OkHttp3Utils.doGet(API.TYPE_PATH + "&gc_id=" + list.get(position).getGc_id(), new GsonObjectCallback<DateGridBean>() {
            @Override
            public void onUi(DateGridBean dateGridBean) {
                myHolder.gv.setAdapter(new MyAdapter_TypeGridView(context, dateGridBean.getDatas().getClass_list()));

            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
      */

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void initThreeadapter(DateGridBean dateGridBean) {
//        myHolder.gv.setAdapter(new MyAdapter_TypeGridView(context, dateGridBean.getDatas().getClass_list()));
    }


    public class MyLeftViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_left_type;
        private GridView gv;

        public MyLeftViewHolder(View itemView) {
            super(itemView);
            tv_left_type = (TextView) itemView.findViewById(R.id.tv_type);
            gv = (GridView) itemView.findViewById(R.id.type_son);
        }
    }

    //声明成员变量
    public OnRecycleViewItemClickListener recycleViewItemClickListener;

    //定义点击接口
    public interface OnRecycleViewItemClickListener {
        void recycleViewItemClickListener(int position, View view, RecyclerView.ViewHolder viewHolder);
    }

    //提供set方法
    public void setRecycleViewItemClickListener(OnRecycleViewItemClickListener recycleViewItemClickListener) {
        this.recycleViewItemClickListener = recycleViewItemClickListener;
    }

}
