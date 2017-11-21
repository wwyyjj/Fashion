package com.five.fashion.cart.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.five.fashion.R;
import com.five.fashion.cart.adapter.ShopcartAdapter;
import com.five.fashion.cart.cartbean.CartDelbean;
import com.five.fashion.cart.cartbean.GoodsInfo;
import com.five.fashion.cart.cartbean.Selbean;
import com.five.fashion.cart.cartbean.StoreInfo;
import com.five.fashion.cart.presenter.Presenter;
import com.five.fashion.mine.utils.UserApi;
import com.five.fashion.sort.bean.AddCartBean;
import com.five.fashion.utils.API;
import com.five.fashion.utils.RetroFactory;
import com.five.fashion.utils.SPUtils;
import com.five.fashion.utils.Toasts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangyajie on 2017/11/9.
 */

public class CartFrag extends Fragment implements ShopcartAdapter.CheckInterface,
        ShopcartAdapter.ModifyCountInterface, ShopcartAdapter.GroupEdtorListener, Iview {
    @BindView(R.id.leftImageView)
    ImageView leftImageView;
    @BindView(R.id.titleEditText)
    EditText titleEditText;
    @BindView(R.id.rightImageView)
    ImageView rightImageView;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.top_bar)
    LinearLayout topBar;
    @BindView(R.id.exListView)
    ExpandableListView exListView;
    @BindView(R.id.all_chekbox)
    CheckBox allChekbox;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_go_to_pay)
    TextView tvGoToPay;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.ll_shar)
    LinearLayout llShar;

    private Context context;
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    private ShopcartAdapter selva;
    private List<StoreInfo> groups = new ArrayList<StoreInfo>();// 组元素数据列表
    private Map<String, List<GoodsInfo>> children = new HashMap<String, List<GoodsInfo>>();// 子元素数据列表
    private int flag = 0;
    public static final String TAG = "CartFrag";
    private List<Selbean.DataBean> data;
    private String uid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_fragment, null, false);
        ButterKnife.bind(this, view);
        context = getActivity();
        return view;
    }
    //注册接口添加适配器
    private void initEvents() {
        selva = new ShopcartAdapter(groups, children, context);
        selva.setCheckInterface(this);// 关键步骤1,设置复选框接口
        selva.setModifyCountInterface(this);// 关键步骤2,设置数量增减接口
        selva.setmListener(this);
        exListView.setAdapter(selva);
        for (int i = 0; i < selva.getGroupCount(); i++) {
            exListView.expandGroup(i);// 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        String o1 = (String) SPUtils.get(getActivity(), UserApi.ISLOGIN, "1");
        if ("0".equals(o1)) {
            uid = (String) SPUtils.get(getActivity(), UserApi.UID, "923");
            new Presenter(this).initselmodel(API.SELCTCART + "?uid=" + uid);
            setCartNum();
        }
    }

    /**
     * 设置购物车产品数量
     */
    private void setCartNum() {
        int count = 0;
        for (int i = 0; i < groups.size(); i++) {
            groups.get(i).setChoosed(allChekbox.isChecked());
            StoreInfo group = groups.get(i);
            List<GoodsInfo> childs = children.get(group.getId());
            for (GoodsInfo goodsInfo : childs) {
                count += 1;
            }
        }
        title.setText("购物车" + "(" + count + ")");
    }

    /**
     * 模拟数据<br>
     * 遵循适配器的数据列表填充原则，组元素被放在一个List中，对应的组元素下辖的子元素被放在Map中，<br>
     * 其键是组元素的Id(通常是一个唯一指定组元素身份的值)
     */
    private void initDatas() {
        /*for (int i = 0; i < 3; i++) {
            groups.add(new StoreInfo(i + "", "天猫店铺" + (i + 1) + "号店"));
            List<GoodsInfo> products = new ArrayList<GoodsInfo>();
            for (int j = 0; j <= i; j++) {
                int[]   img={R.drawable.goods1,R.drawable.goods2,R.drawable.goods3,R.drawable.goods4,R.drawable.goods5,R.drawable.goods6};
                products.add(new GoodsInfo(j + "", "商品", groups.get(i)
                        .getName() + "的第" + (j + 1) + "个商品", 12.00 + new Random().nextInt(23), new Random().nextInt(5) + 1, "豪华", "1", img[i*j],6.00+ new Random().nextInt(13)));
            }
            children.put(groups.get(i).getId(), products);// 将组元素的一个唯一值，这里取Id，作为子元素List的Key
        }*/
        Observable<Selbean> selCartbean = RetroFactory.getInstance().getSelCartbean(API.SELCTCART + "?uid=923");
        selCartbean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Selbean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(final Selbean datarightBean) {
                        data = datarightBean.getData();

                        Log.e(TAG, "onNext: " + datarightBean.getData().toString());
                        for (int i = 0; i < data.size(); i++) {
                            groups.add(new StoreInfo(data.get(i).getSellerid(), data.get(i).getSellerName()));
                            List<GoodsInfo> products = new ArrayList<GoodsInfo>();
                            List<Selbean.DataBean.ListBean> arr = data.get(i).getList();

                            for (int j = 0; j < arr.size(); j++) {
                                String imgs = arr.get(j).getImages();
                                String[] img = imgs.split("\\|");
                                products.add(new GoodsInfo(arr.get(j).getPid() + "", "商品", arr.get(j).getTitle(), arr.get(j).getPrice(), arr.get(j).getNum() + 1, "豪华", "1", null, 6.00 + arr.get(j).getBargainPrice()));
                            }
                            children.put(groups.get(i).getId(), products);// 将组元素的一个唯一值，这里取Id，作为子元素List的Key
                        }
                        initEvents();

                    }
                });


    }

    /**
     * 删除操作<br>
     * 1.不要边遍历边删除，容易出现数组越界的情况<br>
     * 2.现将要删除的对象放进相应的列表容器中，待遍历完后，以removeAll的方式进行删除
     */
    protected void doDelete() {
        List<StoreInfo> toBeDeleteGroups = new ArrayList<StoreInfo>();// 待删除的组元素列表
        for (int i = 0; i < groups.size(); i++) {
            StoreInfo group = groups.get(i);
            if (group.isChoosed()) {
                toBeDeleteGroups.add(group);
            }
            List<GoodsInfo> toBeDeleteProducts = new ArrayList<GoodsInfo>();// 待删除的子元素列表
            List<GoodsInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                if (childs.get(j).isChoosed()) {
                    toBeDeleteProducts.add(childs.get(j));
                }
            }
            childs.removeAll(toBeDeleteProducts);
            for (GoodsInfo aa : toBeDeleteProducts) {
                delData(aa.getId());
            }
        }


        groups.removeAll(toBeDeleteGroups);
        selva.notifyDataSetChanged();
        calculate();
    }

//    增加数量
    @Override
    public void doIncrease(int groupPosition, int childPosition,
                           View showCountView, boolean isChecked) {
        GoodsInfo product = (GoodsInfo) selva.getChild(groupPosition,
                childPosition);
        int currentCount = product.getCount();
        currentCount++;
        product.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        String sellerid = groups.get(groupPosition).getId();
        String pid = children.get(sellerid).get(childPosition).getId();
        Updata("",sellerid,pid,"",currentCount);
        selva.notifyDataSetChanged();
        calculate();
    }
//减少数量
    @Override
    public void doDecrease(int groupPosition, int childPosition,
                           View showCountView, boolean isChecked) {

        GoodsInfo product = (GoodsInfo) selva.getChild(groupPosition,
                childPosition);
        int currentCount = product.getCount();
        if (currentCount == 1)
            return;
        currentCount--;
        product.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        String sellerid = groups.get(groupPosition).getId();
        String pid = children.get(sellerid).get(childPosition).getId();
        Updata("",sellerid,pid,"",currentCount);
        selva.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void childDelete(int groupPosition, int childPosition) {


        StoreInfo group = groups.get(groupPosition);
        List<GoodsInfo> childs = children.get(group.getId());
        for (GoodsInfo aa : childs) {
            delData(aa.getId());
        }
        children.get(groups.get(groupPosition).getId()).remove(childPosition);
        if (childs.size() == 0) {
            groups.remove(groupPosition);
        }
        selva.notifyDataSetChanged();

        handler.sendEmptyMessage(0);
    }
    //    修改数据
    public void Updata(String uid,String sellerid,String pid,String selected,int num){
        Observable<AddCartBean> updataCart = RetroFactory.getInstance().getUpdataCart(API.UPDATACART + "?uid=923&sellerid="+sellerid+"&pid="+pid+"&selected=0&num="+num);
        updataCart.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddCartBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onNext: " + e.getMessage());
                    }

                    @Override
                    public void onNext(final AddCartBean datarightBean) {
                        Log.e(TAG, "onNext: 修改信息" + datarightBean.getMsg());
                    }
                });
    }
    //    删除数据库
    public void delData(String id) {
        Log.e(TAG, "delData: 12313132");
        Observable<CartDelbean> delCart = RetroFactory.getInstance().getDelCart(API.DELCART + "?uid=" + API.UID + "&pid=" + id);
        delCart.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CartDelbean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onNext: " + e.getMessage());
                    }

                    @Override
                    public void onNext(final CartDelbean datarightBean) {
                        Log.e(TAG, "onNext: " + datarightBean.getMsg());
                    }
                });
    }

    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        StoreInfo group = groups.get(groupPosition);
        List<GoodsInfo> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).setChoosed(isChecked);
        }
        if (isAllCheck())
            allChekbox.setChecked(true);
        else
            allChekbox.setChecked(false);
        selva.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void checkChild(int groupPosition, int childPosiTion,
                           boolean isChecked) {
        boolean allChildSameState = true;// 判断改组下面的所有子元素是否是同一种状态
        StoreInfo group = groups.get(groupPosition);
        List<GoodsInfo> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            // 不全选中
            if (childs.get(i).isChoosed() != isChecked) {
                allChildSameState = false;
                break;
            }
        }
        //获取店铺选中商品的总金额
        if (allChildSameState) {
            group.setChoosed(isChecked);// 如果所有子元素状态相同，那么对应的组元素被设为这种统一状态
        } else {
            group.setChoosed(false);// 否则，组元素一律设置为未选中状态
        }

        if (isAllCheck()) {
            allChekbox.setChecked(true);// 全选
        } else {
            allChekbox.setChecked(false);// 反选
        }
        selva.notifyDataSetChanged();
        calculate();

    }

    private boolean isAllCheck() {

        for (StoreInfo group : groups) {
            if (!group.isChoosed())
                return false;

        }

        return true;
    }

    /**
     * 全选与反选
     */
    private void doCheckAll() {
        for (int i = 0; i < groups.size(); i++) {
            groups.get(i).setChoosed(allChekbox.isChecked());
            StoreInfo group = groups.get(i);
            List<GoodsInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                childs.get(j).setChoosed(allChekbox.isChecked());
            }
        }
        selva.notifyDataSetChanged();
        calculate();
    }

    /**
     * 统计操作<br>
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
     * 3.给底部的textView进行数据填充
     */
    private void calculate() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < groups.size(); i++) {
            StoreInfo group = groups.get(i);
            List<GoodsInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                GoodsInfo product = childs.get(j);
                if (product.isChoosed()) {
                    totalCount++;
                    totalPrice += product.getPrice() * product.getCount();
                }
            }

        }
        tvTotalPrice.setText("￥" + totalPrice);
        tvGoToPay.setText("去支付(" + totalCount + ")");
    }

    @OnClick({R.id.all_chekbox, R.id.tv_delete, R.id.tv_go_to_pay, R.id.subtitle, R.id.tv_save, R.id.tv_share})
    public void onClick(View view) {
        AlertDialog alert;
        switch (view.getId()) {
            case R.id.all_chekbox:
                doCheckAll();
                break;
            case R.id.tv_delete:
                if (totalCount == 0) {
                    Toast.makeText(context, "请选择要移除的商品", Toast.LENGTH_LONG).show();
                    return;
                }
                alert = new AlertDialog.Builder(context).create();
                alert.setTitle("操作提示");
                alert.setMessage("您确定要将这些商品从购物车中移除吗？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                doDelete();
                            }
                        });
                alert.show();
                break;
            case R.id.tv_go_to_pay:
                if (totalCount == 0) {
                    Toast.makeText(context, "请选择要支付的商品", Toast.LENGTH_LONG).show();
                    return;
                }
                alert = new AlertDialog.Builder(context).create();
                alert.setTitle("操作提示");
                alert.setMessage("总计:\n" + totalCount + "种商品\n" + totalPrice + "元");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adddetail();
                            }
                        });
                alert.show();
                break;
            case R.id.subtitle:
                if (flag == 0) {
                    llInfo.setVisibility(View.GONE);
                    tvGoToPay.setVisibility(View.GONE);
                    llShar.setVisibility(View.VISIBLE);
                    subtitle.setText("完成");
                } else if (flag == 1) {
                    llInfo.setVisibility(View.VISIBLE);
                    tvGoToPay.setVisibility(View.VISIBLE);
                    llShar.setVisibility(View.GONE);
                    subtitle.setText("编辑");
                }
                flag = (flag + 1) % 2;//其余得到循环执行上面2个不同的功能
                break;
            case R.id.tv_share:
                if (totalCount == 0) {
                    Toast.makeText(context, "请选择要分享的商品", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(context, "分享成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_save:
                if (totalCount == 0) {
                    Toast.makeText(context, "请选择要保存的商品", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void adddetail() {
        Observable<AddCartBean> order = RetroFactory.getInstance().getcreateOrder(API.CREATEORDER+"?uid="+uid+"&price="+totalPrice);
        order.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddCartBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onNext: " + e.getMessage());
                    }

                    @Override
                    public void onNext(final AddCartBean datarightBean) {
                        Log.e(TAG, "onNext: 创建订单" + datarightBean.getMsg());
                        Toasts.showShort(getActivity(),datarightBean.getMsg());
                    }
                });
    }


    public void groupEdit(int groupPosition) {
        groups.get(groupPosition).setIsEdtor(true);
        selva.notifyDataSetChanged();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //删除购物车后动态改变数量
            setCartNum();
        }
    };

    //查询购物车显示数据
    @Override
    public void initAdapterData(Selbean selbean) {
        Log.e(TAG, "initAdapterData: "+selbean.toString() );
        data = selbean.getData();
        groups.removeAll(groups);
        Log.e(TAG, "onNext: " + selbean.getData().toString());
        for (int i = 0; i < data.size(); i++) {
            groups.add(new StoreInfo(data.get(i).getSellerid(), data.get(i).getSellerName()));
            List<GoodsInfo> products = new ArrayList<GoodsInfo>();
            List<Selbean.DataBean.ListBean> arr = data.get(i).getList();

            for (int j = 0; j < arr.size(); j++) {
                String[] split = arr.get(j).getImages().split("\\|");
                products.add(new GoodsInfo(arr.get(j).getPid() + "", "商品", arr.get(j).getTitle().substring(0, 20), arr.get(j).getPrice(), arr.get(j).getNum() , "豪华", "1", split[0], 6.00 + arr.get(j).getBargainPrice()));
            }
            children.put(groups.get(i).getId(), products);// 将组元素的一个唯一值，这里取Id，作为子元素List的Key
        }
        initEvents();
    }
}
