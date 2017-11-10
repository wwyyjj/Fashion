package com.five.fashion.home.presenter;

import com.five.fashion.home.bean.Homebean;
import com.five.fashion.home.model.HomeModel;
import com.five.fashion.home.view.HomeIView;

/**
 * Created by wangyajie on 2017/11/9.
 */

public class Presenter implements HomeModel.initBean{
    HomeModel homeModel;
    HomeIView homeIView;

    public Presenter(HomeIView homeIView) {
        this.homeIView = homeIView;
        this.homeModel=new HomeModel(this);
    }
    public void setUrl(String url){
        homeModel.initData(url);
    }
    @Override
    public void getData(Homebean.DataBean data) {
        homeIView.initAdapterData(data);
    }
}
