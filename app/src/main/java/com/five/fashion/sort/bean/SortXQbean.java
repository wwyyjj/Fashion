package com.five.fashion.sort.bean;

/**
 * Created by wangyajie on 2017/11/16.
 */

public class SortXQbean {

    /**
     * msg :
     * seller : {"description":"我是商家18","icon":"http://120.27.23.105/images/icon.png","name":"商家18","productNums":999,"score":5,"sellerid":18}
     * code : 0
     * data : {"bargainPrice":111.99,"createtime":"2017-10-14T21:39:05","detailUrl":"https://item.m.jd.com/product/4719303.html?utm_source=androidapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=QQfriends","images":"https://m.360buyimg.com/n0/jfs/t9004/210/1160833155/647627/ad6be059/59b4f4e1N9a2b1532.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t7504/338/63721388/491286/f5957f53/598e95f1N7f2adb87.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t7441/10/64242474/419246/adb30a7d/598e95fbNd989ba0a.jpg!q70.jpg","itemtype":1,"pid":2,"price":299,"pscid":1,"salenum":999,"sellerid":18,"subhead":"每个中秋都不能简单，无论身在何处，你总需要一块饼让生活更圆满，京东月饼让爱更圆满京东自营，闪电配送，更多惊喜，快用手指戳一下","title":"北京稻香村 稻香村中秋节月饼 老北京月饼礼盒655g"}
     */

    private String msg;
    private SellerBean seller;
    private String code;
    private DataBean data;

    @Override
    public String toString() {
        return "SortXQbean{" +
                "msg='" + msg + '\'' +
                ", seller=" + seller +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SellerBean getSeller() {
        return seller;
    }

    public void setSeller(SellerBean seller) {
        this.seller = seller;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class SellerBean {


        /**
         * description : 我是商家18
         * icon : http://120.27.23.105/images/icon.png
         * name : 商家18
         * productNums : 999
         * score : 5
         * sellerid : 18
         */

        private String description;
        private String icon;
        private String name;
        private String productNums;
        private String score;
        private String sellerid;

        @Override
        public String toString() {
            return "SellerBean{" +
                    "description='" + description + '\'' +
                    ", icon='" + icon + '\'' +
                    ", name='" + name + '\'' +
                    ", productNums=" + productNums +
                    ", score=" + score +
                    ", sellerid=" + sellerid +
                    '}';
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProductNums() {
            return productNums;
        }

        public void setProductNums(String productNums) {
            this.productNums = productNums;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getSellerid() {
            return sellerid;
        }

        public void setSellerid(String sellerid) {
            this.sellerid = sellerid;
        }
    }

    public static class DataBean {
        /**
         * bargainPrice : 111.99
         * createtime : 2017-10-14T21:39:05
         * detailUrl : https://item.m.jd.com/product/4719303.html?utm_source=androidapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=QQfriends
         * images : https://m.360buyimg.com/n0/jfs/t9004/210/1160833155/647627/ad6be059/59b4f4e1N9a2b1532.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t7504/338/63721388/491286/f5957f53/598e95f1N7f2adb87.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t7441/10/64242474/419246/adb30a7d/598e95fbNd989ba0a.jpg!q70.jpg
         * itemtype : 1
         * pid : 2
         * price : 299
         * pscid : 1
         * salenum : 999
         * sellerid : 18
         * subhead : 每个中秋都不能简单，无论身在何处，你总需要一块饼让生活更圆满，京东月饼让爱更圆满京东自营，闪电配送，更多惊喜，快用手指戳一下
         * title : 北京稻香村 稻香村中秋节月饼 老北京月饼礼盒655g
         */

        private double bargainPrice;
        private String createtime;
        private String detailUrl;
        private String images;
        private String itemtype;
        private String pid;
        private String price;
        private String pscid;
        private String salenum;
        private String sellerid;
        private String subhead;
        private String title;

        @Override
        public String toString() {
            return "DataBean{" +
                    "bargainPrice=" + bargainPrice +
                    ", createtime='" + createtime + '\'' +
                    ", detailUrl='" + detailUrl + '\'' +
                    ", images='" + images + '\'' +
                    ", itemtype=" + itemtype +
                    ", pid=" + pid +
                    ", price=" + price +
                    ", pscid=" + pscid +
                    ", salenum=" + salenum +
                    ", sellerid=" + sellerid +
                    ", subhead='" + subhead + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }

        public double getBargainPrice() {
            return bargainPrice;
        }

        public void setBargainPrice(double bargainPrice) {
            this.bargainPrice = bargainPrice;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getDetailUrl() {
            return detailUrl;
        }

        public void setDetailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getItemtype() {
            return itemtype;
        }

        public void setItemtype(String itemtype) {
            this.itemtype = itemtype;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPscid() {
            return pscid;
        }

        public void setPscid(String pscid) {
            this.pscid = pscid;
        }

        public String getSalenum() {
            return salenum;
        }

        public void setSalenum(String salenum) {
            this.salenum = salenum;
        }

        public String getSellerid() {
            return sellerid;
        }

        public void setSellerid(String sellerid) {
            this.sellerid = sellerid;
        }

        public String getSubhead() {
            return subhead;
        }

        public void setSubhead(String subhead) {
            this.subhead = subhead;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
