package com.five.fashion.cart.model;

import android.util.Log;

import com.five.fashion.cart.cartbean.Selbean;
import com.five.fashion.utils.RetroFactory;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangyajie on 2017/11/17.
 */

public class CartSelModel implements CartImodel {
    public setSelData setSeldata;
    public static final String TAG = "CartSelModel";

    public CartSelModel(setSelData setSeldata) {
        this.setSeldata = setSeldata;
    }

    public interface setSelData {
        void getData(Selbean selbean);
    }

    @Override
    public void initData(String url) {
        Log.e(TAG, "initData: "+url );
        Observable<Selbean> selCartbean = RetroFactory.getInstance().getSelCartbean(url);
        selCartbean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Selbean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e.getMessage() );
                    }

                    @Override
                    public void onNext(final Selbean datarightBean) {
                        Log.e(TAG, "onNext: " + datarightBean.toString());
                        setSeldata.getData(datarightBean);
                    }
                });
    }
}
