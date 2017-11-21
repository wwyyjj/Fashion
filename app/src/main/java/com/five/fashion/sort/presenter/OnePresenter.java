package com.five.fashion.sort.presenter;

import com.five.fashion.sort.bean.SortOnebean;
import com.five.fashion.sort.model.SortOneModel;
import com.five.fashion.sort.view.OneIView;

/**
 * Created by wangyajie on 2017/11/13.
 */

public class OnePresenter implements SortOneModel.initone {
    OneIView oneIView;
    SortOneModel sortOneModel;

    public OnePresenter(OneIView oneIView) {
        this.oneIView = oneIView;
        this.sortOneModel = new SortOneModel(this);
    }

    public void initOneModel() {
        sortOneModel.initone();
    }

    @Override
    public void getOneData(SortOnebean dataleftBean) {
        oneIView.initOneadapter(dataleftBean);
    }
}
