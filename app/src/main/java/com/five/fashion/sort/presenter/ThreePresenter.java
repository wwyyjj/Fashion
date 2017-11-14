package com.five.fashion.sort.presenter;

import com.five.fashion.sort.bean.DateGridBean;
import com.five.fashion.sort.model.SortThreeModel;
import com.five.fashion.sort.model.ThreeModel;
import com.five.fashion.sort.view.ThreeIView;

/**
 * Created by wangyajie on 2017/11/13.
 */

public class ThreePresenter implements SortThreeModel.initThree{
    ThreeIView threeIView;
    ThreeModel threeModel;

    public ThreePresenter(ThreeIView threeIView) {
        this.threeIView = threeIView;
        this.threeModel=new SortThreeModel(this);
    }
    public void initThreeModel(String url) {
        threeModel.initData(url);
    }

    @Override
    public void getThreeData(DateGridBean dateGridBean) {
        threeIView.initThreeadapter(dateGridBean);
    }
}
