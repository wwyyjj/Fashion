package com.five.fashion.utils;

import com.five.fashion.cart.cartbean.CartDelbean;
import com.five.fashion.cart.cartbean.Selbean;
import com.five.fashion.order.bean.orderListBean;
import com.five.fashion.sort.bean.AddCartBean;
import com.five.fashion.sort.bean.SongListbean;
import com.five.fashion.sort.bean.SortOnebean;
import com.five.fashion.sort.bean.SortTwobean;
import com.five.fashion.sort.bean.SortXQbean;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by fan on 2017/11/8.
 */

public interface SortApiServer {
//    分类左边
    @GET
    Observable<SortOnebean> getLeft(@Url String url);
//    分类右边
    @GET
    Observable<SortTwobean> getRight(@Url String url);
//详情
    @GET
    Observable<SortXQbean> getThree(@Url String url);
//分类列表
    @GET
    Observable<SongListbean> getSongList(@Url String url);
//添加购物车
    @GET
    Observable<AddCartBean> getAddCart(@Url String url);
//查询购物车
    @GET
    Observable<Selbean> getSelCartbean(@Url String url);
//删除购物车
    @GET
    Observable<CartDelbean> getDelCart(@Url String url);
//    修改购物车
    @GET
    Observable<AddCartBean> getUpdataCart(@Url String url);
//    创建订单/修改订单
    @GET
    Observable<AddCartBean> getcreateOrder(@Url String url);
//    订单列表
    @GET
    Observable<orderListBean> getOrderList(@Url String url);

}
