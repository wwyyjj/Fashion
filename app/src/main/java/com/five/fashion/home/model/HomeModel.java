package com.five.fashion.home.model;

import com.five.fashion.home.bean.Homebean;
import com.five.fashion.home.utils.RetroFactory;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangyajie on 2017/11/9.
 */

public class HomeModel implements HomeIModel {


    private initBean initbean;
    public HomeModel(initBean initbean) {
        this.initbean = initbean;
    }
    public interface initBean {
        void getData(Homebean.DataBean data);
    }

    @Override
    public void initData(String url) {
        final Observable<Homebean> homeData = RetroFactory.getInstance().getHomeData();
        homeData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Homebean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Homebean homebean) {
                Homebean.DataBean data = homebean.getData();
                initbean.getData(data);
            }
        });

      /*  OkHttp3Utils.getInstance().doGet(url, new GsonObjectCallback<Homebean>() {
            @Override
            public void onUi(Homebean homebean) {
                Homebean.DataBean data = homebean.getData();
                initbean.getData(data);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });*/
    }



}
