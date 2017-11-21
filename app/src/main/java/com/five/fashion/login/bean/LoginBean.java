package com.five.fashion.login.bean;

/**
 * Created by wangyajie on 2017/10/19.
 */

public class LoginBean {


    /**
     * msg : 登录成功
     * code : 0
     * data : {"age":null,"appkey":null,"appsecret":null,"createtime":"2017-11-19T14:09:48","email":null,"gender":0,"icon":null,"mobile":"15701572168","money":0,"nickname":null,"password":"123456","token":"5C6E0D726693CA561820CB2589E4AAA6","uid":923,"username":"15701572168"}
     */

    private String msg;
    private String code;
    private DataBean data;

    @Override
    public String toString() {
        return "LoginBean{" +
                "msg='" + msg + '\'' +
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

    public static class DataBean {
        /**
         * age : null
         * appkey : null
         * appsecret : null
         * createtime : 2017-11-19T14:09:48
         * email : null
         * gender : 0
         * icon : null
         * mobile : 15701572168
         * money : 0
         * nickname : null
         * password : 123456
         * token : 5C6E0D726693CA561820CB2589E4AAA6
         * uid : 923
         * username : 15701572168
         */

        private Object age;
        private Object appkey;
        private Object appsecret;
        private String createtime;
        private Object email;
        private String gender;
        private Object icon;
        private String mobile;
        private String money;
        private Object nickname;
        private String password;
        private String token;
        private String uid;
        private String username;

        @Override
        public String toString() {
            return "DataBean{" +
                    "age=" + age +
                    ", appkey=" + appkey +
                    ", appsecret=" + appsecret +
                    ", createtime='" + createtime + '\'' +
                    ", email=" + email +
                    ", gender='" + gender + '\'' +
                    ", icon=" + icon +
                    ", mobile='" + mobile + '\'' +
                    ", money='" + money + '\'' +
                    ", nickname=" + nickname +
                    ", password='" + password + '\'' +
                    ", token='" + token + '\'' +
                    ", uid='" + uid + '\'' +
                    ", username='" + username + '\'' +
                    '}';
        }

        public Object getAge() {
            return age;
        }

        public void setAge(Object age) {
            this.age = age;
        }

        public Object getAppkey() {
            return appkey;
        }

        public void setAppkey(Object appkey) {
            this.appkey = appkey;
        }

        public Object getAppsecret() {
            return appsecret;
        }

        public void setAppsecret(Object appsecret) {
            this.appsecret = appsecret;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public Object getIcon() {
            return icon;
        }

        public void setIcon(Object icon) {
            this.icon = icon;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
