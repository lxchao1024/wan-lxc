package com.guagua.guagua.gradle363.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class HorizonScrollTextView extends TextView {
	private boolean mStopMarquee;  
    private String mText;
    private float mCoordinateX;  
    private float mTextWidth;
    private float y = 0f;// 文字的纵坐标
   
    public HorizonScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        y = getTextSize() + getPaddingTop();
    }


    public void setText(String text) {
        this.mText = text;  
        mTextWidth = getPaint().measureText(mText);  
        if (mHandler.hasMessages(0))  
            mHandler.removeMessages(0);  
        mHandler.sendEmptyMessageDelayed(0, 2000);  
    }  
   
    
    @Override
    protected void onAttachedToWindow() {  
        mStopMarquee = false;  
        if (!(mText == null || mText.isEmpty()))  
            mHandler.sendEmptyMessageDelayed(0, 2000);  
        super.onAttachedToWindow();  
    }  
   
   
    @Override
    protected void onDetachedFromWindow() {  
        mStopMarquee = true;  
        if (mHandler.hasMessages(0))  
            mHandler.removeMessages(0);  
        super.onDetachedFromWindow();  
    }  
   
   
  
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);  
        if (!(mText == null || mText.isEmpty())) {
            canvas.drawText(mText, mCoordinateX, y, getPaint());
            Log.d("HSTV", "calculateScrollingLen= " + calculateScrollingLen());
        }
    }  
   
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {  
            case 0:  
                if (Math.abs(mCoordinateX) > (mTextWidth + 5)) {
                    mCoordinateX = 0;  
                    invalidate();  
                    if (!mStopMarquee) {  
                        sendEmptyMessageDelayed(0,500);  
                    }  
                } else {  
                    mCoordinateX -= 10;
                    invalidate();  
                    if (!mStopMarquee) {  
                        sendEmptyMessageDelayed(0, 30);  
                    }  
                }  
                break;  
            }  
            super.handleMessage(msg);  
        }  
    };

    /**
     * 计算滚动的距离
     *
     * @return 滚动的距离
     */
    private int calculateScrollingLen() {
        TextPaint tp = getPaint();
        Rect rect = new Rect();
        String strTxt = getText().toString();
        tp.getTextBounds(mText, 0, strTxt.length(), rect);
        return rect.width();
    }
}
