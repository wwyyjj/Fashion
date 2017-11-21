package com.five.fashion.sort.model;

import com.five.fashion.sort.bean.SortOnebean;
import com.five.fashion.utils.API;
import com.five.fashion.utils.RetroFactory;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangyajie on 2017/11/13.
 */

public class SortOneModel implements  Onemodel{
    private initone initone;
    public SortOneModel(SortOneModel.initone initone) {
        this.initone = initone;
    }

    public interface initone{
        void getOneData(SortOnebean dataleftBean);
    }
    @Override
    public void initone() {
       /* OkHttp3Utils.getInstance().doGet(API.TYPE_PATH, new GsonObjectCallback<DataleftBean>() {
            @Override
            public void onUi(final DataleftBean dataleftBean) {
                initone.getOneData(dataleftBean);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });*/
//        //创建Retrofit
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(API.SORTIP)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        //通过动态代理得到网络接口对象
//        SortApiServer apiServer = retrofit.create(SortApiServer.class);
//
//        Observable<SortOnebean> left = apiServer.getLeft(API.SORTBODY);
        Observable<SortOnebean> left = RetroFactory.getInstance().getLeft(API.SORTBODY);
        left.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SortOnebean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SortOnebean sortOnebean) {
                        initone.getOneData(sortOnebean);
                    }
                });
    }
}
