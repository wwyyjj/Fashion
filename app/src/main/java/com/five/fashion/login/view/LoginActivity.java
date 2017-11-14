package com.five.fashion.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.five.fashion.R;
import com.five.fashion.login.bean.LoginBean;
import com.five.fashion.login.presenter.LoginPresenter;
import com.five.fashion.mine.bean.EventBusUserNameBean;
import com.five.fashion.mine.utils.UserApi;
import com.five.fashion.signin.view.SignInActivity;
import com.five.fashion.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements IView{

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
    private String pwd;
    private HashMap<String, String> map;
    private LoginPresenter loginPresenter;
    private String name;
    private String pwd1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lo_gin);
        ButterKnife.bind(this);
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
                initname();
                loginPresenter.initmodledata(map);
                break;
            case R.id.qq_dl:
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
        if ("0".equals(loginBean.getCode())){
            SPUtils.put(this, UserApi.ISLOGIN,loginBean.getCode());
            EventBus.getDefault().post(new EventBusUserNameBean(name));
          //  startActivity(new Intent(this, MainActivity.class));
            finish();
            Log.e("LoginActivity", "isLogin: "+loginBean.getMsg());
        }else {
            Log.e("LoginActivity", "isLogin: "+loginBean.getMsg() );
        }
    }
}
