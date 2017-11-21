package com.five.fashion.order.presenter;

import com.five.fashion.order.bean.orderListBean;
import com.five.fashion.order.model.orderModel;
import com.five.fashion.order.view.IView;

/**
 * Created by wangyajie on 2017/11/21.
 */

public class Presenter implements orderModel.getOrderList{
    orderModel ordermodel;
    IView iView;

    public Presenter(IView iView) {
        this.iView = iView;
        this.ordermodel=new orderModel(this);
    }

    public void initmodel(String url){
        ordermodel.initData(url);
    }

    @Override
    public void getorderlist(orderListBean orderlistBean) {
        iView.initAdapter(orderlistBean);
    }
}
