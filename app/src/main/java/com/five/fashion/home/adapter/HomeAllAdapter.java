package com.five.fashion.home.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.five.fashion.R;
import com.five.fashion.home.bean.Homebean;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyajie on 2017/10/23.
 */

public class HomeAllAdapter extends RecyclerView.Adapter {
    Context context;
    Homebean.DataBean data;
    private ArrayList mlist;

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public HomeAllAdapter(Context context, Homebean.DataBean data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i==0){
            BannerHolder bannerHolder = new BannerHolder(LayoutInflater.from(context).inflate(R.layout.home_item_banner,viewGroup, false));
            return bannerHolder;
        }if (i==1){
            RecyclerHolder holder = new RecyclerHolder(LayoutInflater.from(context).inflate(R.layout.home_item_recycler, viewGroup, false));
            return holder;
        }if (i==2){
            TextViewHolder holder = new TextViewHolder(LayoutInflater.from(context).inflate(R.layout.home_item_textview, viewGroup, false));
            return holder;
        }if (i==3){
            TextViewHolder holder = new TextViewHolder(LayoutInflater.from(context).inflate(R.layout.home_item_textview, viewGroup, false));
            return holder;
        }if (i==4){
            RecyclerHolder holder = new RecyclerHolder(LayoutInflater.from(context).inflate(R.layout.home_item_recycler, viewGroup, false));
            return holder;
        }if (i==5){
            TextViewHolder holder = new TextViewHolder(LayoutInflater.from(context).inflate(R.layout.home_item_textview, viewGroup, false));
            return holder;
        }if (i==6){
            ImageViewHolder holder = new ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.home_item_hot_image, viewGroup, false));
            return holder;
        }if (i==7){
            RecyclerHolder holder = new RecyclerHolder(LayoutInflater.from(context).inflate(R.layout.home_item_recycler, viewGroup, false));
            return holder;
        }if (i==8){
            ImageViewHolder holder = new ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.home_item_hot_image, viewGroup, false));
            return holder;
        }if (i==9){
            RecyclerHolder holder = new RecyclerHolder(LayoutInflater.from(context).inflate(R.layout.home_item_recycler, viewGroup, false));
            return holder;
        }if (i==10){
            TextViewHolder holder = new TextViewHolder(LayoutInflater.from(context).inflate(R.layout.home_item_textview, viewGroup, false));
            return holder;
        }if (i==11){
            RecyclerHolder holder = new RecyclerHolder(LayoutInflater.from(context).inflate(R.layout.home_item_recycler, viewGroup, false));
            return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int a) {
        if (getItemViewType(a)==0) {
            mlist = new ArrayList();
            for(int i=0;i<data.getAd1().size();i++){
                mlist.add(data.getAd1().get(i).getImage());
            }
            //设置图片加载器
            ((BannerHolder) viewHolder).banner.setImageLoader(new GlideImaGlideImageLoader());
            ((BannerHolder) viewHolder).banner.setImages(mlist);
            ((BannerHolder) viewHolder).banner.start();
        } else if (getItemViewType(a)==1) {
            List<Homebean.DataBean.Ad5Bean> ad5 = data.getAd5();
            ((RecyclerHolder) viewHolder).recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
            ((RecyclerHolder) viewHolder).recyclerView.setAdapter(new HomeAdapter(context, ad5));
        }else if (getItemViewType(a)==2) {

           ((TextViewHolder) viewHolder).textView.setText("热门活动");
        } else if(getItemViewType(a)==3) {
//            结束时间
            ((TextViewHolder) viewHolder).textView.setText(data.getGoodsSpreeActivity().getEndDate().toString());
        } else if(getItemViewType(a)==4) {
            List<Homebean.DataBean.ActivityInfoBean.ActivityInfoListBean> infoList = data.getActivityInfo().getActivityInfoList();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            ((RecyclerHolder) viewHolder).recyclerView.setLayoutManager(linearLayoutManager);
            ((RecyclerHolder) viewHolder).recyclerView.setAdapter(new HomeHotImageAdapter(context, infoList));
        }else if(getItemViewType(a)==5) {
            //            结束时间
            ((TextViewHolder) viewHolder).textView.setText("热门专题");
        }else if(getItemViewType(a)==6) {
            //            结束时间
            Glide.with(context).load(data.getSubjects().get(0).getImage()).into( ((ImageViewHolder) viewHolder).img);
        }else if(getItemViewType(a)==7) {
            List<Homebean.DataBean.SubjectsBean.GoodsListBeanX> goodsList = data.getSubjects().get(0).getGoodsList();
            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(context);
            linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
            ((RecyclerHolder) viewHolder).recyclerView.setLayoutManager(linearLayoutManager2);
            ((RecyclerHolder) viewHolder).recyclerView.setAdapter(new HomeHotNewsAdapter(context, goodsList));
        }else if(getItemViewType(a)==8) {
            // Glide.with(getActivity()).load(subjects.get(1).getDescImage()).into(homeHotZhuantiImage2);
            Glide.with(context).load(data.getSubjects().get(1).getDescImage()).into( ((ImageViewHolder) viewHolder).img);
        }else if(getItemViewType(a)==9) {
            List<Homebean.DataBean.SubjectsBean.GoodsListBeanX> goodsList = data.getSubjects().get(1).getGoodsList();
            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(context);
            linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
            ((RecyclerHolder) viewHolder).recyclerView.setLayoutManager(linearLayoutManager2);
            ((RecyclerHolder) viewHolder).recyclerView.setAdapter(new HomeHotNewsAdapter(context, goodsList));
        }else if(getItemViewType(a)==10) {
            //            结束时间
            ((TextViewHolder) viewHolder).textView.setText("猜你喜欢");
        }else if(getItemViewType(a)==11) {
            List<Homebean.DataBean.SubjectsBean.GoodsRelationListBean> list = data.getSubjects().get(1).getGoodsRelationList();
            ((RecyclerHolder) viewHolder).recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            ((RecyclerHolder) viewHolder).recyclerView.setAdapter(new HomeloveAdapter(context, list));
        }
    }

    @Override
    public int getItemCount() {
        return 12;
    }
    class BannerHolder extends RecyclerView.ViewHolder {

        Banner banner;

        public BannerHolder(View view) {
            super(view);
            banner = (Banner) view.findViewById(R.id.mybanner);

        }
    }
    class RecyclerHolder extends RecyclerView.ViewHolder{
        RecyclerView recyclerView;

        public RecyclerHolder(View view) {
            super(view);
            recyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);

        }
    }
    class TextViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public TextViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.home_item_textview);

        }
    }
    class ImageViewHolder extends RecyclerView.ViewHolder{
        ImageView img;

        public ImageViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.home_item_hot_image);

        }
    }
}
