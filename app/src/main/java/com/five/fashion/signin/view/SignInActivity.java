package com.five.fashion.signin.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.five.fashion.R;
import com.five.fashion.login.view.LoginActivity;
import com.five.fashion.signin.bean.ZCBean;
import com.five.fashion.signin.presenter.SignPresenter;
import com.five.fashion.utils.Toasts;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity implements SView {

    public static final String TAG="SignInActivity";
    @BindView(R.id.zc_title_back)
    TextView zcTitleBack;
    @BindView(R.id.zc_username)
    EditText zcUsername;
    @BindView(R.id.zc_password)
    EditText zcPassword;
    @BindView(R.id.zc_password2)
    EditText zcPassword2;
    @BindView(R.id.zc_email)
    EditText zcEmail;
    @BindView(R.id.zc_btn)
    Button zcBtn;
    private HashMap<String, String> map;
    private SignPresenter signPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        signPresenter = new SignPresenter(this);

    }
    @Override
    public void isSign(ZCBean zcBean) {
        Log.e(TAG, "isSign: " + zcBean.toString());
        if ("0".equals(zcBean.getCode())){
            startActivity(new Intent(SignInActivity.this, LoginActivity.class));
            finish();
        }else {
            Toasts.showShort(this,zcBean.getMsg());
        }
    }

    @OnClick({R.id.zc_title_back, R.id.zc_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zc_title_back:
                break;
            case R.id.zc_btn:
                initname();
                signPresenter.initmodledata(map);
                break;
        }
    }
    private void initname() {
        String name = zcUsername.getText().toString().trim();
        String pwd = zcPassword.getText().toString().trim();
        map = new HashMap<>();
        map.put("mobile",name);
        map.put("password",pwd);
    }
}
