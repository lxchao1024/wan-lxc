package com.guagua.guagua.gradle363.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guagua.guagua.gradle363.R;

public class SwitchViewCopy extends LinearLayout {
    private ImageView maskImage;              // 开关遮盖图片
    private boolean open;                     // 开关当前状态
    private boolean isAninFinish = true;      // 动画是否结束
    private float x;                          // 记录ACTION_DOWN时候的横坐标
    private boolean isChangedByTouch = false; // 是否在一次事件中已经切换过状态
    private OnSwitchChangeListener switchChangeListener; // 监控开关状态

    private TextView maskText;

    public interface OnSwitchChangeListener {
        void onSwitchChanged(boolean open);
    }

    public SwitchViewCopy(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwitchViewCopy(Context context) {
        super(context);
        init();
    }

    private void init() {
        setBackgroundResource(R.drawable.switch_bg_selected);
        maskImage = new ImageView(getContext());
        maskImage.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        maskImage.setImageResource(R.drawable.switch_white_circle_normal);
        addView(maskImage);
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
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                x = event.getX();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (event.getX() - x > 5 && !open) { // 向右
                    changeStatus();
                } else if (event.getX() - x < -5 && open) { // 向左
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
                    Animation.ABSOLUTE, getWidth() - maskImage.getWidth(),
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
                    maskImage.clearAnimation();
                    setGravity(Gravity.RIGHT);
                    isAninFinish = true;
                }
            });
            maskImage.startAnimation(leftToRight);
        } else {
            // 右到左
            Animation rightToLeft = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
                    Animation.ABSOLUTE, maskImage.getWidth() - getWidth(),
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
                    maskImage.clearAnimation();
                    setGravity(Gravity.LEFT);
                    isAninFinish = true;
                }
            });
            maskImage.startAnimation(rightToLeft);
        }
    }

    public OnSwitchChangeListener getSwitchChangeListener() {
        return switchChangeListener;
    }

    public void setOnSwitchChangeListener(OnSwitchChangeListener switchChangeListener) {
        this.switchChangeListener = switchChangeListener;
    }

}