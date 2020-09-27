package com.guagua.guagua.gradle363.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.guagua.guagua.gradle363.R;

/**
 * @author lxc
 */
public class MarqueeTextView1 extends AppCompatTextView {

    private Paint mPaint;
    private Paint mPaint2;
    private String mContent;
    private Rect mTargetRect;
    private Rect mTargetRect2;
    private int mBaseLine, mBaseLine2;

    private int mOffset;
    private int mOffset2;
    private int viewWidth;

    private int mTextWidth;
    private ValueAnimator mAnimator;
    private ValueAnimator mAnimator2;

    public MarqueeTextView1(Context context) {
        this(context, null);
    }

    public MarqueeTextView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setTextSize(getContext().getResources().getDimension(R.dimen.sp16));
        mPaint.setColor(Color.RED);
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setAntiAlias(true);

        mPaint2 = new Paint();
        mPaint2.setTextSize(getContext().getResources().getDimension(R.dimen.sp16));
        mPaint2.setColor(Color.RED);
        mPaint2.setTextAlign(Paint.Align.LEFT);
        mPaint2.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mContent == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }

        viewWidth = getMeasuredWidth();
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        int height = fontMetrics.bottom - fontMetrics.top + getPaddingBottom() + getPaddingTop();
        setMeasuredDimension(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));

        int top = getPaddingTop();
        int bottom = top + fontMetrics.bottom - fontMetrics.top;
        int left = getPaddingLeft() + mOffset;
        int right = left + mTextWidth;
        if (mTargetRect == null) {
            mTargetRect = new Rect();
        }
        if (mTargetRect2 == null) {
            mTargetRect2 = new Rect();
        }
        mTargetRect.set(left, top, right, bottom);
        mTargetRect2.set(left, top, right, bottom);
        mBaseLine = (mTargetRect.bottom + mTargetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        mBaseLine2 = (mTargetRect2.bottom + mTargetRect2.top - fontMetrics.bottom - fontMetrics.top) / 2;
        mAnimator.cancel();
        mAnimator2.cancel();
        mAnimator.start();
        mAnimator2.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mContent == null || mTargetRect == null || mTargetRect2 == null) {
            return;
        }

        mTargetRect.left = getPaddingLeft() + mOffset;
        mTargetRect.right = mTargetRect.left + mTextWidth;
        canvas.drawText(mContent, mTargetRect.left, mBaseLine, mPaint);

        if (mTextWidth < viewWidth) {
            mTargetRect2.right = viewWidth + mOffset2;
            mTargetRect2.left = mTargetRect2.right - mTextWidth - getPaddingRight();
        } else {
            mTargetRect2.right = mTextWidth + viewWidth / 3 + mOffset2;
            mTargetRect2.left = mTargetRect2.right - mTextWidth - getPaddingRight();
        }
        canvas.drawText(mContent, mTargetRect2.right, mBaseLine2, mPaint2);
    }

    public void setMarqueeText(String text) {
        mContent = text;
        mTextWidth = (int) (mPaint.measureText(mContent, 0, mContent.length()) + 1);
        if (null == mAnimator) {
            mAnimator = ValueAnimator.ofFloat(0, mTextWidth);
            mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    mOffset -= 2;
                    if (mTextWidth < getWidth()) {
                        if (mOffset < -getWidth()) {
                            mOffset = getWidth();//定位到最右边
                        }
                    } else {
                        if (mOffset < -(getWidth() / 3 + mTextWidth)) {
                            mOffset = mTextWidth + getWidth() / 3;//保证与第二个动画同步
                        }
                    }
                    invalidate();
                }
            });
            mAnimator.setRepeatCount(ValueAnimator.INFINITE);
            mAnimator.setRepeatMode(ValueAnimator.REVERSE);
            //5.为ValueAnimator设置目标对象并开始执行动画
            mAnimator.setTarget(this);
            mAnimator.setDuration((long) (mTextWidth));
        }
        if (null == mAnimator2) {
            mAnimator2 = ValueAnimator.ofFloat(getWidth(), 0);
            mAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    mOffset2 -= 2;
                    if (mTextWidth < getWidth()) {
                        Log.e("MarqueeTextView", "mOffset = " + mOffset2 + ", getWidth() = " + getWidth() + ", mTextWidth = " + mTextWidth + ", viewWidth = " + viewWidth);
                        if (mOffset2 < -2 * getWidth()) {
                            mOffset2 = 0;//定位到最右边
                        }
                    } else {
//                        Log.e("MarqueeTextView", "mOffset = " + mOffset2 + ", getWidth() = " + getWidth() + ", mTextWidth = " + mTextWidth);
                        if (mOffset2 < -2 * (getWidth() / 3 + mTextWidth)) {
                            mOffset2 = 0;
                        }
                    }
                    invalidate();
                }
            });
            mAnimator2.setRepeatCount(ValueAnimator.INFINITE);
            mAnimator2.setRepeatMode(ValueAnimator.REVERSE);
            //5.为ValueAnimator设置目标对象并开始执行动画
            mAnimator2.setTarget(this);
            mAnimator2.setDuration((long) (mTextWidth));
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        releaseAnim();
    }

    public void releaseAnim() {
        if (null != mAnimator) {
            mAnimator.cancel();
        }
        if (null != mAnimator2) {
            mAnimator2.cancel();
        }
    }
}
