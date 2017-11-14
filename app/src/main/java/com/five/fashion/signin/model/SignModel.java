package com.five.fashion.signin.model;

import android.util.Log;

import com.five.fashion.mine.utils.RetroFactory;
import com.five.fashion.mine.utils.UserApi;
import com.five.fashion.signin.bean.ZCBean;

import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangyajie on 2017/11/13.
 */

public class SignModel implements Smodel {

    public static final String TAG = "SignModel";
    private setSignData setSigndata;

    public SignModel(setSignData setSigndata) {
        this.setSigndata = setSigndata;
    }

    public interface setSignData {
        void SignData(ZCBean zcBean);
    }

    @Override
    public void initSmodelData(Map<String, String> map) {
        Observable<ZCBean> signIn = RetroFactory.getInstance().getSignIn(UserApi.SIGNIN, map);
        signIn.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZCBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ZCBean zcBean) {
                        Log.e(TAG, "onNext: " + zcBean.toString());
                        setSigndata.SignData(zcBean);
                    }
                });
    }
}
