package com.five.fashion.order.bean;

import java.util.List;

/**
 * Created by wangyajie on 2017/11/21.
 */

public class orderListBean {

    /**
     * msg : 请求成功
     * code : 0
     * data : [{"createtime":"2017-11-20T14:19:40","orderid":2509,"price":99.99,"status":0,"title":null,"uid":923},{"createtime":"2017-11-21T10:21:52","orderid":2532,"price":99.99,"status":0,"title":null,"uid":923},{"createtime":"2017-11-21T10:23:22","orderid":2533,"price":45646,"status":0,"title":null,"uid":923}]
     * page : 1
     */

    private String msg;
    private String code;
    private String page;
    private List<DataBean> data;

    @Override
    public String toString() {
        return "orderListBean{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", page='" + page + '\'' +
                ", data=" + data +
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

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createtime : 2017-11-20T14:19:40
         * orderid : 2509
         * price : 99.99
         * status : 0
         * title : null
         * uid : 923
         */

        private String createtime;
        private String orderid;
        private double price;
        private String status;
        private Object title;
        private String uid;

        @Override
        public String toString() {
            return "DataBean{" +
                    "createtime='" + createtime + '\'' +
                    ", orderid=" + orderid +
                    ", price=" + price +
                    ", status=" + status +
                    ", title=" + title +
                    ", uid=" + uid +
                    '}';
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}
