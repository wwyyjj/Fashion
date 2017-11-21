package com.five.fashion.sort.bean;

/**
 * Created by wangyajie on 2017/11/17.
 */

public class AddCartBean {

    /**
     * msg : 加购成功
     * code : 0
     */

    private String msg;
    private String code;

    @Override
    public String toString() {
        return "AddCartBean{" +
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
