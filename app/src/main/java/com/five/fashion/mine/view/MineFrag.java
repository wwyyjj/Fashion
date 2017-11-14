package com.five.fashion.mine.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.five.fashion.R;
import com.five.fashion.login.view.LoginActivity;
import com.five.fashion.mine.bean.EventBusUserNameBean;
import com.five.fashion.mine.utils.UserApi;
import com.five.fashion.utils.SPUtils;
import com.five.fashion.utils.Toasts;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fragment, null, false);
        ButterKnife.bind(this, view);
        //注册EventBus事件
        EventBus.getDefault().register(this);
        return view;
    }


    @OnClick(R.id.user_head_image)
    public void onViewClicked() {
        String o = (String) SPUtils.get(getActivity(), UserApi.ISLOGIN, "1");
        if ("0".equals(o))
            Toasts.showShort(getActivity(), "已登录");
            startActivity(new Intent(getActivity(), LoginActivity.class));

    }

    //    事件订阅者处理事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(EventBusUserNameBean eventBusUserNameBean){
        userHeadTextView.setText(eventBusUserNameBean.getName());
        Toasts.showShort(getActivity(),eventBusUserNameBean.getName().toString());
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }

}
