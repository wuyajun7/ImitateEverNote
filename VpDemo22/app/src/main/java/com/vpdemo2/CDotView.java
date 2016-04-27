package com.vpdemo2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wuyajun on 16/4/26.
 * <p/>
 * 游标 Dot 点
 */
public class CDotView extends View {

    private Paint mDotPaint;
    private int mDotColor = 0XFFFFFFFF;
    private int mDotAlpha = 0;

    private Paint mDHPaint;
    private int mDhColor = 0XFFFFFFFF;
    private int mDhAlpha = 0;
    private float mDhStrokeWidth = 7f;

    private boolean mShowDH = false;

    public CDotView(Context context) {
        this(context, null);
        init();
    }

    public CDotView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public CDotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDHPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制圆点
        drawDot(canvas);

        if (mShowDH) {
            drawDh(canvas); //绘制对号
        }
    }

    private void drawDot(Canvas canvas) {

        mDotPaint.setColor(mDotColor);
        mDotPaint.setAlpha(mDotAlpha);
        canvas.drawCircle(getWidth() / 2f, getWidth() / 2f, getWidth() / 2f, mDotPaint);
    }

    //绘制对号
    private void drawDh(Canvas canvas) {

        mDHPaint.setColor(mDhColor);
        mDHPaint.setAntiAlias(true);
        mDHPaint.setStrokeWidth(mDhStrokeWidth);
        mDHPaint.setStrokeCap(Paint.Cap.ROUND);

        float x1 = getMeasuredWidth() * 27 / 100;
        float y1 = getMeasuredWidth() * 48 / 100;

        float x2 = getMeasuredWidth() * 43 / 100;
        float y2 = getMeasuredWidth() * 64 / 100;

        float x3 = getMeasuredWidth() * 78 / 100;
        float y3 = getMeasuredWidth() * 34 / 100;

        canvas.drawLine(
                x1,
                y1,
                x2,
                y2,
                mDHPaint);
        canvas.drawLine(
                x2,
                y2,
                x3,
                y3,
                mDHPaint);

        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
    }

    public void fixDotColor(int color) {
        this.mDotColor = color;
        postInvalidate();
    }

    public void fixDotAlpha(int alpha) {
        this.mDotAlpha = alpha;
        postInvalidate();
    }

    public void fixDHShow(boolean showDH) {
        this.mShowDH = showDH;
        postInvalidate();
    }

    public void fixDHColor(int color) {
        this.mDhColor = color;
        postInvalidate();
    }
}