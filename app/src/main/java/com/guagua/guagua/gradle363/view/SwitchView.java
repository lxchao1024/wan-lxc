package com.guagua.guagua.gradle363.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guagua.guagua.gradle363.R;

public class SwitchView extends LinearLayout {
    /**
     * 开关遮盖图片
     */
    private TextView maskText;
    /**
     * 开关当前状态
     */
    private boolean open;
    /**
     * 动画是否结束
     */
    private boolean isAninFinish = true;
    /**
     * 记录ACTION_DOWN时候的横坐标
     */
    private float x;
    /**
     * 是否在一次事件中已经切换过状态
     */
    private boolean isChangedByTouch = false;
    /**
     * 监控开关状态
     */
    private OnSwitchChangeListener switchChangeListener;

    private Paint paint;
    private String leftStr = "公";
    private String rightStr = "我";
    private float defaultTextSize = 52f;
    private int maskResources = R.drawable.switch_white_circle_normal;
    private int maskTextColor = Color.parseColor("#ffffff");

    public interface OnSwitchChangeListener {
        void onSwitchChanged(boolean open);
    }

    public SwitchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwitchView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setGravity(Gravity.CENTER_VERTICAL);

        paint = new Paint();
        paint.setColor(maskTextColor);
        paint.setTextSize(defaultTextSize);

        maskText = new TextView(getContext());
        maskText.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        maskText.setBackgroundResource(maskResources);
        maskText.setText("公");
        maskText.setTextSize(13f);
        maskText.setTextColor(Color.parseColor("#282828"));
        maskText.setGravity(Gravity.CENTER);
        addView(maskText);
    }

    public boolean getSwitchStatus() {
        return open;
    }

    public void setSwitchStatus(boolean isOpen) {
        this.open = isOpen;
        if (isOpen) {
            setGravity(Gravity.RIGHT);
        } else {
            setGravity(Gravity.LEFT);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(leftStr, getMeasuredWidth() / 4f - (defaultTextSize / 2), getMeasuredHeight() / 2 + 20, paint);
        canvas.drawText(rightStr, getMeasuredWidth() / 4f * 3f - (defaultTextSize / 2), getMeasuredHeight() / 2 + 20, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                x = event.getX();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (event.getX() - x > 5 && !open) {
                    // 向右
                    changeStatus();
                } else if (event.getX() - x < -5 && open) {
                    // 向左
                    changeStatus();
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                if (Math.abs(event.getX() - x) <= 5) {
                    changeStatus();
                }
                isChangedByTouch = false;
                break;
            }
            case MotionEvent.ACTION_CANCEL: {
                isChangedByTouch = false;
                break;
            }
            default:
                break;
        }
        return true;
    }

    private void changeStatus() {
        if (isAninFinish && !isChangedByTouch) {
            isChangedByTouch = true;
            open = !open;
            isAninFinish = false;
            if (switchChangeListener != null) {
                switchChangeListener.onSwitchChanged(open);
            }
            changeOpenStatusWithAnim(open);
        }
    }

    private void changeOpenStatusWithAnim(boolean open) {
        if (open) {
            // 左到右
            Animation leftToRight = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
                    Animation.ABSOLUTE, getWidth() - maskText.getWidth(),
                    Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_SELF, 0);
            leftToRight.setDuration(300);
            leftToRight.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    maskText.clearAnimation();
                    setGravity(Gravity.RIGHT);
                    maskText.setText(rightStr);
                    isAninFinish = true;
                }
            });
            maskText.startAnimation(leftToRight);
        } else {
            // 右到左
            Animation rightToLeft = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
                    Animation.ABSOLUTE, maskText.getWidth() - getWidth(),
                    Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_SELF, 0);
            rightToLeft.setDuration(300);
            rightToLeft.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    maskText.clearAnimation();
                    setGravity(Gravity.LEFT);
                    maskText.setText(leftStr);
                    isAninFinish = true;
                }
            });
            maskText.startAnimation(rightToLeft);
        }
    }

    public OnSwitchChangeListener getSwitchChangeListener() {
        return switchChangeListener;
    }

    public void setOnSwitchChangeListener(OnSwitchChangeListener switchChangeListener) {
        this.switchChangeListener = switchChangeListener;
    }

    public void setLeftStr(String leftStr) {
        this.leftStr = leftStr;
        postInvalidate();
    }

    public void setRightStr(String rightStr) {
        this.rightStr = rightStr;
        postInvalidate();
    }

    public void setMaskTextColor(int maskTextColor) {
        this.maskTextColor = maskTextColor;
        postInvalidate();
    }

    public void setDefaultTextSize(float defaultTextSize) {
        this.defaultTextSize = defaultTextSize;
        postInvalidate();
    }

    public void setMaskResources(int maskResources) {
        this.maskResources = maskResources;
        postInvalidate();
    }
}