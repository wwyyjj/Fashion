package com.five.fashion.cart.presenter;

import android.util.Log;

import com.five.fashion.cart.cartbean.Selbean;
import com.five.fashion.cart.model.CartSelModel;
import com.five.fashion.cart.view.Iview;

/**
 * Created by wangyajie on 2017/11/17.
 */

public class Presenter implements CartSelModel.setSelData {
    Iview iview;
    CartSelModel cartSelModel;
    public static final String TAG = "cartPresenter";

    public Presenter(Iview iview) {
        this.iview = iview;
        this.cartSelModel = new CartSelModel(this);
    }

    public void initselmodel(String url) {
        Log.e(TAG, "initselmodel: " + url);
        cartSelModel.initData(url);
    }

    @Override
    public void getData(Selbean selbean) {
        Log.e(TAG, "getData: "+selbean );
        iview.initAdapterData(selbean);
    }
}
