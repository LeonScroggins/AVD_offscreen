package com.progresss.scroggo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by scroggo on 4/3/19.
 */

public class MyProgressBar extends ProgressBar {
    Rect mHitRect;

    public MyProgressBar(Context context) {
        this(context, null);
    }

    public MyProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHitRect = new Rect();
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth()* 2, getMeasuredHeight());
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(10);
        getHitRect(mHitRect);
        mHitRect.offsetTo(0, 0);
        canvas.drawRect(mHitRect, p);
        canvas.translate(getMeasuredWidth() / 4, 0);
        super.onDraw(canvas);
    }
}
