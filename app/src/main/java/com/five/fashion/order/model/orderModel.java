package com.five.fashion.order.model;

import android.util.Log;

import com.five.fashion.order.bean.orderListBean;
import com.five.fashion.utils.RetroFactory;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangyajie on 2017/11/21.
 */

public class orderModel implements IModel {
    public static final String TAG="orderModel";
    public getOrderList getorderList;

    public orderModel(getOrderList getorderList) {
        this.getorderList = getorderList;
    }

    public interface getOrderList{
        void getorderlist(orderListBean orderlistBean);
    }
    @Override
    public void initData(String url) {
        Observable<orderListBean> orderList = RetroFactory.getInstance().getOrderList(url);
        orderList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<orderListBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onNext: " + e.getMessage());
                    }

                    @Override
                    public void onNext(final orderListBean orderlistBean) {
                        Log.e(TAG, "onNext: " + orderlistBean.getMsg());
                        getorderList.getorderlist(orderlistBean);
                    }
                });
    }
}
