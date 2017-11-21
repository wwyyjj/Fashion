package com.five.fashion.sort.model;

import com.five.fashion.sort.bean.SortTwobean;
import com.five.fashion.utils.RetroFactory;

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
        void getTwoData(SortTwobean datarightBean);
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
//        //创建Retrofit
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(API.SORTIP)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        //通过动态代理得到网络接口对象
//        SortApiServer apiServer = retrofit.create(SortApiServer.class);
//
//        Observable<SortTwobean> left = apiServer.getRight(url);
        Observable<SortTwobean> right = RetroFactory.getInstance().getRight(url);
        right.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SortTwobean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SortTwobean datarightBean) {
                        inittwo.getTwoData(datarightBean);
                    }
                });
    }
}
