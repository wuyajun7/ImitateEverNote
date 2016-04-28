package com.vpdemo2;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NNVipMemberActivity extends FragmentActivity implements View.OnClickListener {

    private LinearLayout mSuperLayout;
    private LinearLayout mVpDotLayout;
    private ViewPager mViewpager;
    private CFragmentAdapter fragmentAdapter;
    private CVPChangeListener pageChangeListener;

    private LinearLayout mVipStartLayout;
    private TextView mVipTextStart,
            mVipTextCurPrice,
            mVipTextOldPrice;
    private LinearLayout mVipServiceLayout;
    private TextView mVipTextService;

    private LayoutInflater mLayoutInflater;
    private NNVipItemFragment fragment;
    private CDotView[] mDotViews;

    private List<Fragment> mFragments;
    private Bundle data;
    private List<NNVipBean> vipBeans;
    private List<NNVipDetail> vipDetails;

    private int mCurIndex;
    private int mCurSelected;

    private int mScrWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_vip_member);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mScrWidth = dm.widthPixels;

        initData();
        initViews();

        findViewById(R.id.change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageChangeListener.refreshDotDH(1);

                setCurView(1);
                setCurDot(1);
            }
        });
        findViewById(R.id.change).setVisibility(View.GONE);
    }

    private void initData() {
        vipBeans = new ArrayList<>();

        NNVipBean vipBean;
        NNVipDetail vipDetail;
        for (int i = 0; i < NNVipData.VIP_LVL_ICO.length; i++) {
            vipBean = new NNVipBean();
            vipBean.setTitle(NNVipData.VIP_LVL_TIT[i]);

            vipDetails = new ArrayList<>();
            for (int j = 0; j < NNVipData.VIP_LVL_ICO[i]; j++) {
                vipDetail = new NNVipDetail();
                vipDetail.setImgId(NNVipData.VIP_IMG_ARR[j]);
                vipDetail.setDescribe(NNVipData.VIP_DETAIL_ARR[j]);
                vipDetails.add(vipDetail);
            }

            vipBean.setVipBeans(vipDetails);
            vipBean.setSortDetailColor(NNVipData.VIP_LVL_TIT_COLS[i]);
            vipBeans.add(vipBean);
        }
    }

    private void initViews() {
        mSuperLayout = (LinearLayout) findViewById(R.id.super_layout);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mVpDotLayout = (LinearLayout) findViewById(R.id.vp_dot_ly);

        mVipStartLayout = (LinearLayout) findViewById(R.id.vip_start_layout);
        mVipStartLayout.setOnClickListener(this);
        mVipTextStart = (TextView) findViewById(R.id.vip_text_start);
        mVipTextCurPrice = (TextView) findViewById(R.id.vip_text_cur_price);
        mVipTextOldPrice = (TextView) findViewById(R.id.vip_text_old_price);
        mVipServiceLayout = (LinearLayout) findViewById(R.id.vip_service_layout);
        mVipServiceLayout.setOnClickListener(this);
        mVipTextService = (TextView) findViewById(R.id.vip_text_service);

        mFragments = new ArrayList<>();
        for (int i = 0; i < vipBeans.size(); i++) {
            fragment = new NNVipItemFragment();
            data = new Bundle();
            data.putSerializable("vipModel", vipBeans.get(i));
            fragment.setArguments(data);
            mFragments.add(fragment);
        }

        //测量TextView 占用宽度
        TextView textView;
        int tvWSum = 0;
        for (int t = 0; t < vipBeans.size(); t++) {
            textView = new TextView(this);
            textView.setText(vipBeans.get(t).getTitle());
            textView.setTextSize(11.0f);
            textView.measure(0, 0);
            tvWSum += textView.getMeasuredWidth();
        }

        mDotViews = new CDotView[vipBeans.size()];
        //循环添加、设置小点
        for (int i = 0; i < vipBeans.size(); i++) {
            if (mLayoutInflater == null) {
                mLayoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            View dotItem = mLayoutInflater.inflate(R.layout.vip_item_dot, null);

            TextView dotText = (TextView) dotItem.findViewById(R.id.di_dot_text);
            dotText.setText(vipBeans.get(i).getTitle());

            CDotView dotView = (CDotView) dotItem.findViewById(R.id.di_dot);
            mDotViews[i] = dotView;
            mDotViews[i].fixDotAlpha(0);

            dotItem.setTag(i);
            dotItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (Integer) v.getTag();
                    setCurView(position);
                    setCurDot(position);
                }
            });
            mVpDotLayout.addView(dotItem);

            if (i < vipBeans.size() - 1) {
                LinearLayout.LayoutParams lineW = new LinearLayout.LayoutParams(
                        (mScrWidth - (dip2px(this, 25) * 2 + tvWSum)) / 2, dip2px(this, 1));
                lineW.setMargins(0, dip2px(this, 15), 0, 0);

                View line = new View(this);
                line.setLayoutParams(lineW);
                line.setBackgroundColor(0X55FFFFFF);
                mVpDotLayout.addView(line);
            }
        }

        fragmentAdapter = new CFragmentAdapter(getSupportFragmentManager(), mFragments);
        mViewpager.setAdapter(fragmentAdapter);

        pageChangeListener = new CVPChangeListener(NNVipData.VIP_LVL_COLS, mDotViews, mCurSelected);
        pageChangeListener.setColorListener(new IBgColorListener() {
            @Override
            public void callBack(int color) {
                mSuperLayout.setBackgroundColor(color);

                mVipTextStart.setTextColor(color);
                mVipTextCurPrice.setTextColor(color);
                mVipTextOldPrice.setTextColor(color);
                mVipTextService.setTextColor(color);
            }
        });
        mViewpager.setOnPageChangeListener(pageChangeListener);

        //初始化已选项颜色及状态
        mDotViews[mCurSelected].fixDHShow(true);
        mDotViews[mCurSelected].fixDHColor(Color.WHITE);
    }

    /**
     * ViewPager 移动到指定位置
     *
     * @param position 指定坐标
     */
    private void setCurView(int position) {
        if (position < 0 || position >= mFragments.size()) {
            return;
        }
        mViewpager.setCurrentItem(position);
    }

    /**
     * 设置当前坐标 Dot 透明度
     *
     * @param position
     */
    private void setCurDot(int position) {
        if (position < 0 || position > mFragments.size() - 1 || mCurIndex == position) {
            return;
        }

        for (int i = 0; i < mDotViews.length; i++) {
            if (i == position) {
                mDotViews[i].fixDotAlpha(255);
            } else {
                mDotViews[i].fixDotAlpha(0);
            }
        }
        mCurIndex = position;
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     */
    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vip_start_layout:
                break;
            case R.id.vip_service_layout:
                break;
        }
    }
}
