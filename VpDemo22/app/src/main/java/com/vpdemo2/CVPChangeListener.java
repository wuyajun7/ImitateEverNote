package com.vpdemo2;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

/**
 * Created by peterwu on 16/4/27.
 * <p/>
 * ViewPager 监听
 */
public class CVPChangeListener implements ViewPager.OnPageChangeListener {

    private ArgbEvaluator mEvaluator;
    private int[] mColors;
    private LinearLayout mSuperLayout;
    private CDotView[] mDotViews;
    private int mCurSelected = 0;


    public CVPChangeListener(int[] mColors, LinearLayout mSuperLayout, CDotView[] mDotViews, int mCurSelected) {
        this.mEvaluator = new ArgbEvaluator();
        this.mColors = mColors;
        this.mSuperLayout = mSuperLayout;
        this.mDotViews = mDotViews;
        this.mCurSelected = mCurSelected;
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int curColor = mColors[position];
        int secColor = position == mColors.length - 1 ? mColors[0] : mColors[position + 1];
        int dhColor = mColors[mCurSelected];

        setSuperBgColor(positionOffset, mEvaluator, curColor, secColor);
        setDotDhColor(position, positionOffset, mEvaluator, dhColor);
        setDotAlpha(position, positionOffset, mEvaluator);
    }

    @Override
    public void onPageSelected(int arg0) {
    }

    /**
     * 设置背景颜色
     *
     * @param positionOffset
     * @param evaluator
     * @param curColor
     * @param secColor
     */
    private void setSuperBgColor(float positionOffset, ArgbEvaluator evaluator, int curColor, int secColor) {
        mSuperLayout.setBackgroundColor(curColor);
        int evaluate = (Integer) evaluator.evaluate(positionOffset, curColor, secColor);
        mSuperLayout.setBackgroundColor(evaluate);
    }

    /**
     * 设置 Dot Alpha 值
     *
     * @param position       当前 Dot 位置
     * @param positionOffset 当前 Dot 偏移量
     * @param evaluator      起始颜色值 - 结束颜色值以及一个偏移量生成一个新的颜色
     */
    private void setDotAlpha(int position, float positionOffset, ArgbEvaluator evaluator) {
        if (position < 0 || position + 1 > mDotViews.length - 1) {
            return;
        }

        int posAlpha = (Integer) evaluator.evaluate(positionOffset, 255, 0);
        int curAlpha = (Integer) evaluator.evaluate(positionOffset, 0, 255);

        mDotViews[position].fixDotAlpha(posAlpha);
        mDotViews[position + 1].fixDotAlpha(curAlpha);

        if (mDotViews != null && mDotViews.length > 0) {
            for (int i = 0; i < mDotViews.length; i++) {
                if (!(i == position || i == position + 1)) {
                    mDotViews[i].fixDotAlpha(0);
                }
            }
        }
    }

    /**
     * 设置 Dot 中“对号” 颜色 值
     *
     * @param position       当前 Dot 位置
     * @param positionOffset 当前 Dot 偏移量
     * @param evaluator      起始颜色值 - 结束颜色值以及一个偏移量生成一个新的颜色
     * @param secColor       偏移量颜色值
     */
    private void setDotDhColor(int position, float positionOffset, ArgbEvaluator evaluator, int secColor) {
        if (position < 0 || position + 1 > mDotViews.length - 1) {
            return;
        }

        int evaluate1 = (Integer) evaluator.evaluate(positionOffset, Color.WHITE, secColor);
        int evaluate2 = (Integer) evaluator.evaluate(positionOffset, secColor, Color.WHITE);

        if (position == mCurSelected) {
            mDotViews[mCurSelected].fixDHColor(evaluate2);
        } else if (position + 1 == mCurSelected) {
            mDotViews[mCurSelected].fixDHColor(evaluate1);
        } else {
            mDotViews[mCurSelected].fixDHColor(Color.WHITE);
        }
    }

    /**
     * 刷新选中坐标
     *
     * @param curSelected
     */
    public void refreshDotDH(int curSelected) {
        this.mCurSelected = curSelected;
        for (int i = 0; i < mDotViews.length; i++) {
            if (i == mCurSelected) {
                mDotViews[i].fixDHShow(true);
                mDotViews[i].fixDHColor(mColors[i]);
            } else {
                mDotViews[i].fixDHShow(false);
            }
        }
    }

}
