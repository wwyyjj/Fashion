package com.five.fashion.sort.show;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.five.fashion.R;
import com.five.fashion.home.adapter.GlideImaGlideImageLoader;
import com.five.fashion.login.view.LoginActivity;
import com.five.fashion.main.MainActivity;
import com.five.fashion.media.IRenderView;
import com.five.fashion.media.IjkVideoView;
import com.five.fashion.mine.utils.UserApi;
import com.five.fashion.sort.bean.AddCartBean;
import com.five.fashion.sort.bean.SortXQbean;
import com.five.fashion.sort.model.SortThreeModel;
import com.five.fashion.sort.model.getListData;
import com.five.fashion.utils.API;
import com.five.fashion.utils.RetroFactory;
import com.five.fashion.utils.SPUtils;
import com.five.fashion.utils.Toasts;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ShowActivity extends AppCompatActivity {
    public static final String TAG = "ShowActivity";
    @BindView(R.id.goodname)
    TextView goodname;
    @BindView(R.id.goodimg)
    TextView goodimg;
    @BindView(R.id.goodbanner)
    Banner goodbanner;
    @BindView(R.id.goodprice)
    TextView goodprice;
    @BindView(R.id.addcart)
    TextView addcart;
    @BindView(R.id.actionShop)
    TextView actionShop;
    @BindView(R.id.startijkplayer)
    TextView videoView;
    @BindView(R.id.IjkVideoView)
    IjkVideoView qqq;
    private String pid;
    private String uid;
    private String o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        //注册事件
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        o = (String) SPUtils.get(ShowActivity.this, UserApi.ISLOGIN, "1");
        uid = (String) SPUtils.get(ShowActivity.this, UserApi.UID, "923");
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMoonEvent(final showXQbean messageEvent) {
        new SortThreeModel().initData(API.SORTXQ + "?pid=" + messageEvent.getPid(), new getListData() {
            @Override
            public void getData(SortXQbean dateGridBean) {
                pid = messageEvent.getPid();
                Log.e(TAG, "getData: " + pid);
                String s = dateGridBean.getData().getImages().toString();
                String[] split = s.split("\\|");
                List<String> list = Arrays.asList(split);
                goodname.setText(dateGridBean.getSeller().getName());
                goodimg.setText(dateGridBean.getData().getTitle());
                goodprice.setText("￥: " + dateGridBean.getData().getPrice());
                //设置图片加载器
                goodbanner.setImageLoader(new GlideImaGlideImageLoader());
                goodbanner.setImages(list);
                goodbanner.start();
            }
        });
    }

    @OnClick({R.id.startijkplayer, R.id.addcart, R.id.actionShop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.startijkplayer:
                qqq.setAspectRatio(IRenderView.AR_ASPECT_FIT_PARENT);
                qqq.setVideoURI(Uri.parse("http://mp4.vjshi.com/2013-05-28/2013052815051372.mp4"));
                qqq.start();
                break;
            case R.id.addcart:
                if ("0".equals(o)) {
                    addcart();
                } else {
                    Toasts.showShort(ShowActivity.this,"请登录");
                    startActivity(new Intent(ShowActivity.this, LoginActivity.class));
                }
                break;
            case R.id.actionShop:

                if ("0".equals(o)) {
                    addcart();
                    startActivity(new Intent(ShowActivity.this, MainActivity.class));

                    finish();
                } else {
                    Toasts.showShort(ShowActivity.this,"请登录");
                    startActivity(new Intent(ShowActivity.this, LoginActivity.class));
                }

                break;
        }
    }

    private void addcart() {
        Observable<AddCartBean> addCart = RetroFactory.getInstance().getAddCart(API.ADDCART + "?uid=" + uid + "&pid=" + pid);
        addCart.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddCartBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(final AddCartBean datarightBean) {
                        Toasts.showShort(ShowActivity.this, datarightBean.getMsg());
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
