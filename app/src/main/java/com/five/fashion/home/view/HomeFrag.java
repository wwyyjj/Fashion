package com.five.fashion.home.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.five.fashion.R;
import com.five.fashion.home.adapter.HomeAllAdapter;
import com.five.fashion.home.bean.Homebean;
import com.five.fashion.home.presenter.Presenter;
import com.five.fashion.home.utils.Api;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangyajie on 2017/11/9.
 */

public class HomeFrag extends Fragment implements HomeIView{
    @BindView(R.id.xRecyclerView)
    XRecyclerView xRecyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, null, false);
        ButterKnife.bind(this, view);
//        ininData();
        new Presenter(this).setUrl(Api.HOME_PATH);
        return view;
    }
   /* private void ininData() {
        OkHttp3Utils.getInstance().doGet(UserApi.HOME_PATH, new GsonObjectCallback<Homebean>() {
            @Override
            public void onUi(Homebean homebean) {
                Homebean.DataBean data = homebean.getData();
                xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                xRecyclerView.setAdapter(new HomeAllAdapter(getActivity(), data));
            }
            @Override
            public void onFailed(Call call, IOException e) {
            }
        });
    }*/

    @Override
    public void initAdapterData(Homebean.DataBean data) {
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        xRecyclerView.setAdapter(new HomeAllAdapter(getActivity(), data));
    }
}
