package com.five.fashion.mine.utils;

import com.five.fashion.login.bean.LoginBean;
import com.five.fashion.signin.bean.ZCBean;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by fan on 2017/11/8.
 */

public interface UserApiServer {
    @POST
    Observable<LoginBean> getLogin(@Url String url, @QueryMap Map<String,String> map);
    @POST
    Observable<ZCBean> getSignIn(@Url String url, @QueryMap Map<String,String> map);

}
