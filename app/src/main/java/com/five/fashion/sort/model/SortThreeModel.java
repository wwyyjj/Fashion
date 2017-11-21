package com.five.fashion.sort.model;

import android.util.Log;

import com.five.fashion.sort.bean.SortXQbean;
import com.five.fashion.utils.RetroFactory;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangyajie on 2017/11/14.
 */

public class SortThreeModel implements ThreeModel {
public static final String TAG="SortThreeModel";
    @Override
    public void initData(String url, final getListData getlistData) {
        Observable<SortXQbean> three = RetroFactory.getInstance().getThree(url);
//        //创建Retrofit
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(API.SORTIP)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        //通过动态代理得到网络接口对象
//        SortApiServer apiServer = retrofit.create(SortApiServer.class);
//
//        Observable<SortXQbean> three = apiServer.getThree(url);
        three.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SortXQbean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onNext(final SortXQbean dateGridBean) {
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
