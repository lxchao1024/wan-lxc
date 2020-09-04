package com.guagua.guagua.gradle363.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.guagua.guagua.gradle363.R;

import org.jetbrains.annotations.Nullable;

public class SwitchButton extends View {

    //new的时候调用
    public SwitchButton(Context context) {
        this(context, null);
    }

    //使用style的使用调用
    public SwitchButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //写在布局文件里面的时候调用
    public SwitchButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
    //将图片绘制到控件上面

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //新建一只画笔，并设置为绿色属性
        Paint _paint = new Paint();
        _paint.setColor(Color.GREEN);
        Paint _paint1 = new Paint();
        _paint1.setTextSize(14f);
        _paint1.setColor(Color.WHITE);

        //新建矩形r2
        RectF r2 = new RectF();
        r2.left = 0;
        r2.right = 132;
        r2.top = 0;
        r2.bottom = 72;

        //画出圆角矩形r2
        _paint.setColor(Color.parseColor("#6236FF"));
        canvas.drawRoundRect(r2, 36, 36, _paint);

        canvas.drawText("公", 0,0, _paint1);
    }
}