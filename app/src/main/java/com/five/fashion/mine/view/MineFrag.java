package com.five.fashion.mine.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.five.fashion.R;
import com.five.fashion.login.view.LoginActivity;
import com.five.fashion.mine.utils.UserApi;
import com.five.fashion.order.view.OrderActivity;
import com.five.fashion.utils.SPUtils;
import com.five.fashion.utils.Toasts;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangyajie on 2017/11/9.
 */

public class MineFrag extends Fragment {
    @BindView(R.id.user_head_image)
    ImageView userHeadImage;
    @BindView(R.id.user_head_textView)
    TextView userHeadTextView;
    @BindView(R.id.collectionGoodsTextView)
    TextView collectionGoodsTextView;
    @BindView(R.id.collectionStoreTextView)
    TextView collectionStoreTextView;
    @BindView(R.id.myFootprintTextView)
    TextView myFootprintTextView;
    @BindView(R.id.orderTextView)
    TextView orderTextView;
    @BindView(R.id.orderWaitPayTextView)
    TextView orderWaitPayTextView;
    @BindView(R.id.orderWaitDriveTextView)
    TextView orderWaitDriveTextView;
    @BindView(R.id.orderWaitReceiptTextView)
    TextView orderWaitReceiptTextView;
    @BindView(R.id.orderWaitCommentTextView)
    TextView orderWaitCommentTextView;
    @BindView(R.id.orderWaitRefundTextView)
    TextView orderWaitRefundTextView;
    @BindView(R.id.userMoneyTextView)
    TextView userMoneyTextView;
    @BindView(R.id.propertyMoneyTextView)
    TextView propertyMoneyTextView;
    @BindView(R.id.propertyCardTextView)
    TextView propertyCardTextView;
    @BindView(R.id.propertyVouchersTextView)
    TextView propertyVouchersTextView;
    @BindView(R.id.propertyRedTextView)
    TextView propertyRedTextView;
    @BindView(R.id.propertyIntegralTextView)
    TextView propertyIntegralTextView;
    @BindView(R.id.addressTextView)
    TextView addressTextView;
    @BindView(R.id.settingTextView)
    TextView settingTextView;
    @BindView(R.id.user_main)
    LinearLayout userMain;
    private String o;
    public static final String TAG = "MineFrag";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fragment, null, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        o = (String) SPUtils.get(getActivity(), UserApi.ISLOGIN, "1");

        if ("0".equals("0")) {
            String name = (String) SPUtils.get(getActivity(), UserApi.UNAME, "未登录");
            userHeadTextView.setText(name);
        } else {
            userHeadTextView.setText("未登录");
        }
        boolean o = (boolean) SPUtils.get(getActivity(), UserApi.ISQQ, false);
        if (o == true) {
            String name = (String) SPUtils.get(getActivity(), UserApi.QQNAME, "未获取");
            String img = (String) SPUtils.get(getActivity(), UserApi.QQIMG, null);
            Log.e(TAG, "onResume: QQ的头像" + img);
            userHeadTextView.setText(name);
            Glide.with(getActivity()).load("http://q.qlogo.cn/qqapp/1105602574/01097E9E8E26024944BDC736581FECE1/100").placeholder(R.mipmap.ic_launcher).into(userHeadImage);
        }
    }

    @OnClick(R.id.user_head_image)
    public void onViewClicked() {
        Log.e(TAG, "onViewClicked: " + SPUtils.getAll(getActivity()));
        if ("0".equals(o))
            Toasts.showShort(getActivity(), "已登录");
        startActivity(new Intent(getActivity(), LoginActivity.class));

    }
    @OnClick({R.id.orderTextView, R.id.orderWaitPayTextView, R.id.orderWaitDriveTextView, R.id.orderWaitReceiptTextView, R.id.orderWaitCommentTextView, R.id.orderWaitRefundTextView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.orderTextView:
                startActivity(new Intent(getActivity(), OrderActivity.class));
                break;
            case R.id.orderWaitPayTextView:
                break;
            case R.id.orderWaitDriveTextView:
                break;
            case R.id.orderWaitReceiptTextView:
                break;
            case R.id.orderWaitCommentTextView:
                break;
            case R.id.orderWaitRefundTextView:
                break;
        }
    }
}
