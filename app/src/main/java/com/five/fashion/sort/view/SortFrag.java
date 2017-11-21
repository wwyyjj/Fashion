package com.five.fashion.sort.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.five.fashion.R;
import com.five.fashion.sort.adapter.MyAdapter_left;
import com.five.fashion.sort.adapter.MyAdapter_right;
import com.five.fashion.sort.bean.Datebeanitem;
import com.five.fashion.sort.bean.SortOnebean;
import com.five.fashion.sort.bean.SortTwobean;
import com.five.fashion.sort.presenter.OnePresenter;
import com.five.fashion.sort.presenter.TwoPresenter;
import com.five.fashion.sort.utils.GsonObjectCallback;
import com.five.fashion.sort.utils.OkHttp3Utils;
import com.five.fashion.utils.API;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by wangyajie on 2017/11/9.
 */

public class SortFrag extends Fragment implements OneIView, TwoIView {
    RecyclerView rv_left;
    RecyclerView rv_right;
    private TwoPresenter twoPresenter1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sort_fragment, null, false);
        rv_left=  view.findViewById(R.id.type_rvleft);
        rv_right=  view.findViewById(R.id.type_rvright);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        //得到WindowManager
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        //得到屏幕宽
        int width = display.getWidth();
        //将RecyclerView宽设置为屏幕宽的1/5
        params.width = width * 1 / 5;
        rv_left.setLayoutParams(params);
        //得到RecyclerView布局管理器
        LinearLayoutManager leftLayoutManager = new LinearLayoutManager(getActivity());
        //RecyclerView设置布局管理器
        rv_left.setLayoutManager(leftLayoutManager);
        //得到RecyclerView布局管理器
        LinearLayoutManager rightLayoutManager = new LinearLayoutManager(getActivity());
        //RecyclerView设置布局管理器
        rv_right.setLayoutManager(rightLayoutManager);
        //获取后台数据，添加适配器
        // getServerData();
        // 启动mvp
        new OnePresenter(this).initOneModel();//一级的presenter
        twoPresenter1 = new TwoPresenter(this);//一二的presenter
        twoPresenter1.initTwoModel(API.SORTSONG + "?cid=" + 1);

        return view;
    }

    /*//获取后台数据的方法
    public void getServerData() {
        OkHttp3Utils.getInstance().doGet(API.TYPE_PATH, new GsonObjectCallback<DataleftBean>() {
            @Override
            public void onUi(final DataleftBean dataleftBean) {
                //适配器
                final MyAdapter_left myAdapter_left = new MyAdapter_left(getActivity(), dataleftBean.getDatas().getClass_list());
                rv_left.setAdapter(myAdapter_left);
                //第一个子条目显示其二级数据

                //子条目点击监听
                myAdapter_left.setRecycleViewItemClickListener(new MyAdapter_left.OnRecycleViewItemClickListener() {
                    @Override
                    public void recycleViewItemClickListener(int position, View view, RecyclerView.ViewHolder viewHolder) {
                        myAdapter_left.setTagPosition(position);
                        myAdapter_left.notifyDataSetChanged();
                        //请求二级数据
                        getServerTypeData(dataleftBean.getDatas().getClass_list().get(position).getGc_id(), position);
                    }
                });
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });


    }*/

    //获取网络数据的方法
    public static void getServerData(Context context, String url, final OnGetServerDateLisnter onGetServerDateLisnter) {
        OkHttp3Utils.getInstance().doGet(url, new GsonObjectCallback<Datebeanitem>() {
            @Override
            public void onUi(Datebeanitem datebeanitem) {
                onGetServerDateLisnter.getData(datebeanitem.getDatas().toString());
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

    }

    //    一级列表赋值
    @Override
    public void initOneadapter(final SortOnebean dataleftBean) {
        List<SortOnebean.DataBean> data = dataleftBean.getData();
        //适配器
        final MyAdapter_left myAdapter_left = new MyAdapter_left(getActivity(), dataleftBean.getData());
        rv_left.setAdapter(myAdapter_left);
        //第一个子条目显示其二级数据

        //子条目点击监听
        myAdapter_left.setRecycleViewItemClickListener(new MyAdapter_left.OnRecycleViewItemClickListener() {
            @Override
            public void recycleViewItemClickListener(int position, View view, RecyclerView.ViewHolder viewHolder) {
                myAdapter_left.setTagPosition(position);
                myAdapter_left.notifyDataSetChanged();
                //请求二级数据
                //getServerTypeData(dataleftBean.getDatas().getClass_list().get(position).getGc_id(), position);
                String gc_id = dataleftBean.getData().get(position).getCid();
                twoPresenter1.initTwoModel(API.SORTSONG + "?cid=" + gc_id);
            }
        });
    }

    //    二级列表赋值
    @Override
    public void initTwoadapter(SortTwobean datarightBean) {
        List<SortTwobean.DataBean> data = datarightBean.getData();
        MyAdapter_right myAdapter_right = new MyAdapter_right(getActivity(), datarightBean.getData());
        rv_right.setAdapter(myAdapter_right);
    }

    public interface OnGetServerDateLisnter {
        void getData(String string);
    }

}