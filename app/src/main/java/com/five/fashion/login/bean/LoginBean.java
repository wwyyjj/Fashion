package com.five.fashion.login.bean;

/**
 * Created by wangyajie on 2017/10/19.
 */

public class LoginBean {
    /**
     * msg : 登录成功
     * code : 0
     */

    private String msg;
    private String code;

    public LoginBean() {
    }

    public LoginBean(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
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
