package com.five.fashion.sort.show;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.five.fashion.R;
import com.five.fashion.sort.adapter.HomeAdapter;
import com.five.fashion.sort.bean.SongListbean;
import com.five.fashion.utils.API;
import com.five.fashion.utils.RetroFactory;
import com.five.fashion.utils.Toasts;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ShowListActivity extends AppCompatActivity {
    public static final String TAG = "ShowListActivity";
    @BindView(R.id.sort_show_listView)
    XRecyclerView sortShowListView;
    private HomeAdapter adapter;
    private String id;
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
        ButterKnife.bind(this);
        sortShowListView.setLayoutManager(new LinearLayoutManager(ShowListActivity.this));
        sortShowListView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page++;
                initData(page, id);
                Log.e(TAG, "onRefresh: ");
                adapter.notifyDataSetChanged();
                sortShowListView.refreshComplete();


            }

            @Override
            public void onLoadMore() {
                page++;
                if (page >= 3) {
                    page = 3;
                    Toasts.showShort(ShowListActivity.this, "没有更多了");
                }
                initData(page, id);
                Log.e(TAG, "onLoadMore: ");
                adapter.notifyDataSetChanged();
                sortShowListView.loadMoreComplete();
            }
        });
        //注册事件
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMoonEvent(Showlistbean messageEvent) {
        Log.e(TAG, "onNext: " + messageEvent.getId());
        id = messageEvent.getId();
        //        ?page=1&sort=0&pscid=" + messageEvent.getId()

        initData(page, id);


    }

    private void initData(int page, String id) {
        Observable<SongListbean> songList = RetroFactory.getInstance().getSongList(API.SORTSONGLIST + "?page=" + page + "&sort=0&pscid=" + id);
        songList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SongListbean>() {


                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(final SongListbean datarightBean) {


                        final List<SongListbean.DataBean> data = datarightBean.getData();
                        data.addAll(data);
                        adapter = new HomeAdapter(ShowListActivity.this, data);
                        sortShowListView.setAdapter(adapter);
                        adapter.setmOnItemClickListener(new HomeAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                String pid = datarightBean.getData().get(position).getPid();
                                EventBus.getDefault().postSticky(new showXQbean(pid));
                                Intent intent = new Intent(ShowListActivity.this, ShowActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }


}
