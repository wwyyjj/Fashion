package com.five.fashion.sort.model;

import com.five.fashion.sort.bean.DatarightBean;
import com.five.fashion.sort.utils.API;
import com.five.fashion.sort.utils.SortApiServer;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangyajie on 2017/11/13.
 */

public class SortTwoModel implements TwoModel{

    private initTwo inittwo;

    public SortTwoModel(initTwo inittwo) {
        this.inittwo = inittwo;
    }

    public interface initTwo{
        void getTwoData(DatarightBean datarightBean);
    }
    @Override
    public void initTwo(String url) {
       /* OkHttp3Utils.doGet(url, new GsonObjectCallback<DatarightBean>() {
            @Override
            public void onUi(DatarightBean datarightBean) {
                inittwo.getTwoData(datarightBean);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });*/
        //创建Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.TYPE_IP)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        //通过动态代理得到网络接口对象
        SortApiServer apiServer = retrofit.create(SortApiServer.class);

        Observable<DatarightBean> left = apiServer.getRight(url);
        left.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DatarightBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DatarightBean datarightBean) {
                        inittwo.getTwoData(datarightBean);
                    }
                });
    }
}
