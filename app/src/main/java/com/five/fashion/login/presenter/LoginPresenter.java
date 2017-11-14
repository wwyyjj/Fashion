package com.five.fashion.login.presenter;

import android.util.Log;

import com.five.fashion.login.bean.LoginBean;
import com.five.fashion.login.model.Loginmodel;
import com.five.fashion.login.view.IView;

import java.util.Map;

/**
 * Created by wangyajie on 2017/11/13.
 */

public class LoginPresenter implements Loginmodel.setloginData{
    Loginmodel loginmodel;
    IView iView;

    public LoginPresenter(IView iView) {
        this.iView = iView;
        this.loginmodel=new Loginmodel(this);
    }

    public void initmodledata(Map<String, String> map){
        Log.e("LoginPresenter", "initmodledata: "+map.toString());
        loginmodel.initLoginData(map);
    }

    @Override
    public void loginData(LoginBean loginBean) {
        Log.e("LoginPresenter", "initmodledata: "+loginBean.toString());
        iView.isLogin(loginBean);
    }
}
