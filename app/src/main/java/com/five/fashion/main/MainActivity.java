package com.five.fashion.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.five.fashion.R;
import com.five.fashion.cart.view.CartFrag;
import com.five.fashion.home.view.HomeFrag;
import com.five.fashion.login.view.LoginActivity;
import com.five.fashion.mine.utils.UserApi;
import com.five.fashion.mine.view.MineFrag;
import com.five.fashion.sort.view.SortFrag;
import com.five.fashion.utils.SPUtils;
import com.five.fashion.utils.Toasts;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    @BindView(R.id.main_vp)
    ViewPager mainVp;
    @BindView(R.id.homeRadioButton)
    RadioButton homeRadioButton;
    @BindView(R.id.classRadioButton)
    RadioButton classRadioButton;
    @BindView(R.id.cartRadioButton)
    RadioButton cartRadioButton;
    @BindView(R.id.userRadioButton)
    RadioButton userRadioButton;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragments = new ArrayList<>();
        fragments.add(new HomeFrag());
        fragments.add(new SortFrag());
        fragments.add(new CartFrag());
        fragments.add(new MineFrag());
        //        添加适配器
        mainVp.setAdapter(new MainFraAdapter(getSupportFragmentManager(), fragments));
        //        默认选中一
        radioGroup.check(R.id.homeRadioButton);
        String o = (String) SPUtils.get(this, UserApi.UNAME, "123");
        String o2 = (String) SPUtils.get(this, UserApi.ISLOGIN, "123");
        String o3 = (String) SPUtils.get(this, UserApi.UID, "123");
        Log.e(TAG, "onCreate:UNAME " + o + "----ISLOGIN-" + o2 + "-UID----" + o3);

    }

    @Override
    protected void onResume() {
        super.onResume();
        int page = (int) SPUtils.get(MainActivity.this, "page", 1);
        if (page==3||page==2) {
            mainVp.setCurrentItem(2);
            radioGroup.check(R.id.cartRadioButton);
        }
    }

    @OnClick({R.id.homeRadioButton, R.id.classRadioButton, R.id.cartRadioButton, R.id.userRadioButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.homeRadioButton:
                mainVp.setCurrentItem(0);
                break;
            case R.id.classRadioButton:
                mainVp.setCurrentItem(1);
                break;
            case R.id.cartRadioButton:
                SPUtils.put(MainActivity.this,"page",2);
                String o = (String) SPUtils.get(MainActivity.this, UserApi.ISLOGIN, "1");
                if ("0".equals(o)) {
                    mainVp.setCurrentItem(2);
                } else {
                    Toasts.showShort(MainActivity.this, "请登录");
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
                break;
            case R.id.userRadioButton:
                mainVp.setCurrentItem(3);
                break;
        }
    }
}
