package com.progresss.scroggo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Animatable;
//import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by scroggo on 4/3/19.
 */

public class MyProgressBar extends ProgressBar implements View.OnClickListener {
    final Rect mHitRect;
    final Animatable mDrawable;
    //Handler mHandler;

    public MyProgressBar(Context context) {
        this(context, null);
    }

    public MyProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHitRect = new Rect();
        Drawable d = getIndeterminateDrawable();
        if (d instanceof  Animatable) {
            mDrawable = (Animatable) d;
            setOnClickListener(this);
        } else {
            mDrawable = null;
            log("NOT Animatable?");
        }
    }

    private static void log(String msg) {
        Log.d("SCROGGO", msg);
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

    @Override
    public void onClick(View v) {
        log("click!");
        getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mDrawable.isRunning()) {
                    log("stop");
                    mDrawable.stop();
                } else {
                    log("start");
                    mDrawable.start();
                }
            }
        }, 3000);
    }

/*
    // Looks like View already has a version of this. It can return null, but maybe not in any case
    // that I care about?
    private Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        return mHandler;
    }
*/
}
