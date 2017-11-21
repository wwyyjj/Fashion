package com.five.fashion.utils;

/**
 * autour: 樊彦龙
 * date: 2017/10/20 14:16
 * update: 2017/10/20
 */

public class API {
    //public static final String TYPE_PATH = "http://192.168.28.5/mobile/index.php?act=goods_class";
    public static final String TYPE_PATH = "http://169.254.172.202/mobile/index.php?act=goods_class";
    public static final String TYPE_IP = "http://169.254.172.202/mobile/";
    public static final String TYPE_BODY = "index.php?act=goods_class";
    //    商品分类接口
    //   http://120.27.23.105/product/getCatagory
    public static final String SORTIP = "http://120.27.23.105/";
    public static final String SORTBODY = "product/getCatagory";
    //    商品子分类接口
    //    http://120.27.23.105/product/getProductCatagory
    public static final String SORTSONG = "product/getProductCatagory";
    //商品详情  http://120.27.23.105/product/getProductDetail
    public static final String SORTXQ = "product/getProductDetail";
    //    http://120.27.23.105/product/getProducts?page=1&sort=0&pscid=1
    //    子分类列表
    public static final String SORTSONGLIST = "product/getProducts";
    //    添加购物车
    //    http://120.27.23.105/product/addCart?uid=1&pid=57
    public static final String ADDCART = "product/addCart";
    //    查询购物车
    //    http://120.27.23.105/product/getCarts?uid=923
    public static final String SELCTCART = "product/getCarts";
    //    删除购物车
    //    http://120.27.23.105/product/deleteCart?uid=72&pid=1
    public static final String DELCART = "product/deleteCart";

    public static String UID = "923";
    //    修改
    //    http://120.27.23.105/product/updateCarts?uid=923&sellerid=12&pid=56&selected=0&num=10
    public static final String UPDATACART = "product/updateCarts";
    //    创建订单
    public static final String CREATEORDER = "product/createOrder";
    //    订单列表
    public static final String GETORDERS = "product/getOrders";
    //    修改订单状态
    public static final String UPDATAORDER = "product/updateOrder";
    //  常用收货地址列表
    public static final String GETADDRS = "user/getAddrs";
    //    添加常用收获地址
    public static final String ADDADDR = "user/getAddrs";
    //    修改常用收货地址
    public static final String UPDATAADDADDR = "user/updateAddr";
    //    设置默认收货地址
    public static final String SETADDR = "user/setAddr";
    //    获取默认收货地址
    public static final String GETDEFAULTADDR = "user/getDefaultAddr";

}
