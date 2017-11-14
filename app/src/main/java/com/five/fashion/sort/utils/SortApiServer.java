package com.five.fashion.sort.utils;

import com.five.fashion.sort.bean.DataleftBean;
import com.five.fashion.sort.bean.DatarightBean;
import com.five.fashion.sort.bean.DateGridBean;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by fan on 2017/11/8.
 */

public interface SortApiServer {
    @GET
    Observable<DataleftBean> getLeft(@Url String url);
    @GET
    Observable<DatarightBean> getRight(@Url String url);
    @GET
    Observable<DateGridBean> getThree(@Url String url);
}
