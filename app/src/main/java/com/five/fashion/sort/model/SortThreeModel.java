package com.five.fashion.sort.model;

import com.five.fashion.sort.bean.DateGridBean;
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
 * Created by wangyajie on 2017/11/14.
 */

public class SortThreeModel implements ThreeModel {

    @Override
    public void initData(String url, final getListData getlistData) {
        //创建Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.TYPE_IP)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        //通过动态代理得到网络接口对象
        SortApiServer apiServer = retrofit.create(SortApiServer.class);

        Observable<DateGridBean> three = apiServer.getThree(url);
        three.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DateGridBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(final DateGridBean dateGridBean) {
                       getlistData.getData(dateGridBean);
                    }
                });
    }

  /*  @Override
    public void initData(String url) {
       *//* //第三次请求网络 获取第三级数据
        OkHttp3Utils.doGet(API.TYPE_PATH + "&gc_id=" + list.get(position).getGc_id(), new GsonObjectCallback<DateGridBean>() {
            @Override
            public void onUi(DateGridBean dateGridBean) {
                myHolder.gv.setAdapter(new MyAdapter_TypeGridView(context,dateGridBean.getDatas().getClass_list()));
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });*//*


    }*/
}
