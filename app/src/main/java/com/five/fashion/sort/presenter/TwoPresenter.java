package com.five.fashion.sort.presenter;

import com.five.fashion.sort.bean.DatarightBean;
import com.five.fashion.sort.model.SortTwoModel;
import com.five.fashion.sort.view.TwoIView;

/**
 * Created by wangyajie on 2017/11/13.
 */

public class TwoPresenter implements SortTwoModel.initTwo {
    TwoIView twoIView;
    SortTwoModel sortTwoModel;

    public TwoPresenter(TwoIView twoIView) {
        this.twoIView = twoIView;
        this.sortTwoModel=new SortTwoModel(this);
    }

    public void initTwoModel(String url) {
        sortTwoModel.initTwo(url);
    }



    @Override
    public void getTwoData(DatarightBean datarightBean) {
        twoIView.initTwoadapter(datarightBean);
    }
}
