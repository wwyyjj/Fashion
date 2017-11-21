package com.five.fashion.login.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.five.fashion.R;
import com.five.fashion.home.utils.Toasts;
import com.five.fashion.login.bean.LoginBean;
import com.five.fashion.login.bean.QQloginBean;
import com.five.fashion.login.presenter.LoginPresenter;
import com.five.fashion.mine.utils.UserApi;
import com.five.fashion.signin.view.SignInActivity;
import com.five.fashion.utils.SPUtils;
import com.google.gson.Gson;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity implements IView {

    @BindView(R.id.login_back)
    TextView loginBack;
    @BindView(R.id.login_image)
    ImageView loginImage;
    @BindView(R.id.login_username)
    EditText loginUsername;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_zc)
    TextView loginZc;
    @BindView(R.id.login_dl)
    Button loginDl;
    @BindView(R.id.qq_dl)
    Button qqDl;
    @BindView(R.id.exit_dl)
    Button exitDl;
    private String pwd;
    private HashMap<String, String> map;
    private LoginPresenter loginPresenter;
    private String name;
    private String pwd1;
    public static final String TAG = "LoginActivity";
    private static final String APP_ID = "1105602574";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lo_gin);
        ButterKnife.bind(this);
        //传入参数APPID和全局Context上下文
        mTencent = Tencent.createInstance(APP_ID, LoginActivity.this.getApplicationContext());
        loginPresenter = new LoginPresenter(this);
    }

    @OnClick({R.id.login_zc, R.id.login_dl, R.id.qq_dl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_zc:
                startActivity(new Intent(this, SignInActivity.class));
                finish();
                break;
            case R.id.login_dl:
                String o = (String) SPUtils.get(LoginActivity.this, UserApi.ISLOGIN, "1");
                if ("0".equals(o)) {
                    Toasts.showShort(LoginActivity.this, "已登录");
                    finish();
                } else {
                    initname();
                    loginPresenter.initmodledata(map);
                }
                break;
            case R.id.qq_dl:
                /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
                 官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
                 第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
                mIUiListener = new BaseUiListener();
                //all表示获取所有权限
                mTencent.login(LoginActivity.this,"all", mIUiListener);

                break;
        }
    }

    private void initname() {
        name = loginUsername.getText().toString().trim();
        pwd1 = loginPassword.getText().toString().trim();
        map = new HashMap<>();
        map.put("mobile", name);
        map.put("password", pwd1);
    }


    @Override
    public void isLogin(LoginBean loginBean) {
        String code = loginBean.getCode();
        Toasts.showShort(LoginActivity.this, loginBean.getMsg());
        if ("0".equals(code) == true) {
            SPUtils.put(this, UserApi.ISLOGIN, loginBean.getCode());
            SPUtils.put(this, UserApi.UID, loginBean.getData().getUid());
            SPUtils.put(this, UserApi.UNAME, loginBean.getData().getUsername());

        } else {
            Log.e("LoginActivity", "isLogin: 登录shibai" + loginBean.getMsg());
        }
        finish();
    }

    @OnClick(R.id.exit_dl)
    public void onViewClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.exit);
        builder.setMessage(R.string.isexit);

        //监听下方button点击事件
        builder.setPositiveButton(R.string.postive_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SPUtils.clear(LoginActivity.this);
            }
        });
        builder.setNegativeButton(R.string.negative_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toasts.showShort(getApplicationContext(),"已取消");
            }
        });
        //设置对话框是可取消的
        builder.setCancelable(true);
        AlertDialog dialog=builder.create();
        dialog.show();

    }
    /**
     * 自定义监听器实现IUiListener接口后，需要实现的3个方法
     * onComplete完成 onError错误 onCancel取消
     */
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            Toast.makeText(LoginActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken,expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(),qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        QQloginBean qQloginBean = new Gson().fromJson(response.toString(), QQloginBean.class);
                        Log.e(TAG,"登录成功"+response.toString());
                        SPUtils.put(LoginActivity.this,UserApi.ISQQ,true);
                        SPUtils.put(LoginActivity.this,UserApi.QQNAME,qQloginBean.getNickname());
                        SPUtils.put(LoginActivity.this,UserApi.QQIMG,qQloginBean.getFigureurl_qq_1());
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG,"登录失败"+uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG,"登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(LoginActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this, "授权取消", Toast.LENGTH_SHORT).show();

        }

    }

    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_LOGIN){
            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
