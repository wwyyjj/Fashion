package com.five.fashion.login.model;

import com.five.fashion.login.bean.LoginBean;
import com.five.fashion.mine.utils.RetroFactory;
import com.five.fashion.mine.utils.UserApi;

import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangyajie on 2017/11/13.
 */

public class Loginmodel implements IModel{
    private setloginData setlogindata;

    public Loginmodel(setloginData setlogindata) {
        this.setlogindata = setlogindata;
    }

    public interface setloginData{
        void loginData(LoginBean loginBean);
    }
    @Override
    public void initLoginData(Map<String, String> map) {
        final Observable<LoginBean> login = RetroFactory.getInstance().getLogin(UserApi.LOGIN, map);
        login.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        setlogindata.loginData(loginBean);
                    }
                });
    }


}
