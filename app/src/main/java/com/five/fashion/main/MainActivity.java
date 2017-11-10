package com.five.fashion.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.five.fashion.R;
import com.five.fashion.cart.view.CartFrag;
import com.five.fashion.home.view.HomeFrag;
import com.five.fashion.mine.view.MineFrag;
import com.five.fashion.sort.view.SortFrag;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


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
        mainVp.setAdapter(new MainFraAdapter(getSupportFragmentManager(),fragments));
//        默认选中一
        radioGroup.check(R.id.homeRadioButton);
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
                mainVp.setCurrentItem(2);
                break;
            case R.id.userRadioButton:
                mainVp.setCurrentItem(3);
                break;
        }
    }
}
