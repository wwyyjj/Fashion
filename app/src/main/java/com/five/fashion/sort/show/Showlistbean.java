package com.five.fashion.sort.show;

/**
 * Created by wangyajie on 2017/11/16.
 */

public class Showlistbean {
    private String id;

    public Showlistbean(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Showlistbean{" +
                "id=" + id +
                '}';
    }
}
