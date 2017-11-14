package com.five.fashion.mine.bean;

/**
 * Created by wangyajie on 2017/11/13.
 */

public class EventBusUserNameBean {
    public String name;

    public EventBusUserNameBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EventBusUserNameBean{" +
                "name='" + name + '\'' +
                '}';
    }

    public EventBusUserNameBean() {
    }
}
