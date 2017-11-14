package com.five.fashion.signin.presenter;

import android.util.Log;

import com.five.fashion.signin.bean.ZCBean;
import com.five.fashion.signin.model.SignModel;
import com.five.fashion.signin.view.SView;

import java.util.Map;

/**
 * Created by wangyajie on 2017/11/13.
 */

public class SignPresenter implements SignModel.setSignData{
    SView sView;
    SignModel signModel;
    public static final String TAG="SignPresenter";

    public SignPresenter(SView sView) {
        this.sView = sView;
        this.signModel=new SignModel(this);
    }
    public void initmodledata(Map<String, String> map){
        Log.e("SignPresenter", "initmodledata: "+map.toString());
        signModel.initSmodelData(map);
    }
    @Override
    public void SignData(ZCBean zcBean) {
        sView.isSign(zcBean);
    }
}
