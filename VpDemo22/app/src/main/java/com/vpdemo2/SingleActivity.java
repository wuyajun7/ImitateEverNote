package com.vpdemo2;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class SingleActivity extends FragmentActivity {

    private LinearLayout mSuperLayout;
    private ViewPager mViewpager;
    private LinearLayout mVpDotLayout;

    private CFragmentAdapter fragmentAdapter;

    private NNVipItemFragment fragment1;
    private NNVipItemFragment fragment2;
    private NNVipItemFragment fragment3;

    private List<Fragment> views;

    private int color1 = 0XFF788f95;
    private int color2 = 0XFF3dacb2;
    private int color3 = 0XFF3fbb65;

    private int[] mColors = {color1, color2, color3};

    private int mCurIndex;
    private int mCurSelected;

    private LayoutInflater mLayoutInflater;
    private CDotView[] mDotViews;

    private CVPChangeListener pageChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        findViewById(R.id.change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageChangeListener.refreshDotDH(1);

                setCurView(1);
                setCurDot(1);
            }
        });
    }

    private void initViews() {
        mSuperLayout = (LinearLayout) findViewById(R.id.super_layout);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mVpDotLayout = (LinearLayout) findViewById(R.id.vp_dot_ly);

        fragment1 = new NNVipItemFragment();
        fragment2 = new NNVipItemFragment();
        fragment3 = new NNVipItemFragment();

        views = new ArrayList<>();
        views.add(fragment1);
        views.add(fragment2);
        views.add(fragment3);

        mDotViews = new CDotView[views.size()];
        //循环添加、设置小点
        for (int i = 0; i < views.size(); i++) {
            if (mLayoutInflater == null) {
                mLayoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            View dotItem = mLayoutInflater.inflate(R.layout.vip_item_dot, null);

            LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(
                    dip2px(this, 45),
                    dip2px(this, 45));
            linearParams.setMargins(
                    dip2px(this, 25), 0,
                    dip2px(this, 25), 0);
            dotItem.setLayoutParams(linearParams);

            CDotView dotView = (CDotView) dotItem.findViewById(R.id.di_dot);
            mDotViews[i] = dotView;
            mDotViews[i].fixDotAlpha(0);
            mDotViews[i].setTag(i);
            mDotViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (Integer) v.getTag();
                    setCurView(position);
                    setCurDot(position);
                }
            });
            mVpDotLayout.addView(dotItem);
        }

        fragmentAdapter = new CFragmentAdapter(getSupportFragmentManager(), views);
        mViewpager.setAdapter(fragmentAdapter);

        pageChangeListener = new CVPChangeListener(mColors, mSuperLayout, mDotViews, mCurSelected);
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
        if (position < 0 || position >= views.size()) {
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
        if (position < 0 || position > views.size() - 1 || mCurIndex == position) {
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
     *
     * @param dipValue
     * @param （DisplayMetrics类中属性density）
     * @return
     */
    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
