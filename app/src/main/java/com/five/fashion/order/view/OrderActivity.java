package com.five.fashion.order.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.five.fashion.R;
import com.five.fashion.order.adapter.HomeAdapter;
import com.five.fashion.order.bean.orderListBean;
import com.five.fashion.order.presenter.Presenter;
import com.five.fashion.utils.API;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends AppCompatActivity implements IView {
    public static final String TAG = "OrderActivity";
    @BindView(R.id.order_recyclerview)
    RecyclerView orderRecyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Presenter presenter = new Presenter(this);

        presenter.initmodel(API.GETORDERS + "?uid=923&page=1");
    }

    @Override
    public void initAdapter(orderListBean orderlistBean) {
        List<orderListBean.DataBean> data = orderlistBean.getData();
        orderRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        HomeAdapter homeAdapter = new HomeAdapter(OrderActivity.this, data);
        orderRecyclerview.setAdapter(homeAdapter);
        orderRecyclerview.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        homeAdapter.setmOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });

    }
}
