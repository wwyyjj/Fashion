package com.five.fashion.signin.bean;

/**
 * Created by wangyajie on 2017/10/19.
 */

public class ZCBean {

    /**
     * msg : 天呢！用户已注册
     * code : 1
     */

    private String msg;
    private String code;

    public ZCBean(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public ZCBean() {
    }

    @Override
    public String toString() {
        return "ZCBean{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
