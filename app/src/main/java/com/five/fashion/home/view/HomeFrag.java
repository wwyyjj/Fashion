package com.five.fashion.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.five.fashion.R;
import com.five.fashion.home.adapter.HomeAllAdapter;
import com.five.fashion.home.bean.Homebean;
import com.five.fashion.home.presenter.Presenter;
import com.five.fashion.home.utils.Api;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangyajie on 2017/11/9.
 */

public class HomeFrag extends Fragment implements HomeIView {
    @BindView(R.id.xRecyclerView)
    XRecyclerView xRecyclerView;
    @BindView(R.id.leftImageView)
    ImageView leftImageView;
    @BindView(R.id.titleEditText)
    EditText titleEditText;
    @BindView(R.id.rightImageView)
    ImageView rightImageView;
    private int REQUEST_CODE = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, null, false);
        ButterKnife.bind(this, view);
        new Presenter(this).setUrl(Api.HOME_PATH);
        return view;
    }

    @Override
    public void initAdapterData(Homebean.DataBean data) {
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        xRecyclerView.setAdapter(new HomeAllAdapter(getActivity(), data));
    }

    @OnClick({R.id.leftImageView, R.id.titleEditText, R.id.rightImageView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.leftImageView:
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.titleEditText:
                break;
            case R.id.rightImageView:
                break;
        }
    }
    //    二维码
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
