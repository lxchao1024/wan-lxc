package com.guagua.guagua.gradle363.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import com.guagua.guagua.gradle363.R;

/**
 * Created by dss886 on 15/9/26.
 */
public class EmotionInputDetector {
    private static final String TAG = EmotionInputDetector.class.getSimpleName();
    private static final String SHARE_PREFERENCE_NAME = "com.dss886.emotioninputdetector";
    private static final String SHARE_PREFERENCE_TAG = "soft_input_height";

    private Activity mActivity;
    private InputMethodManager mInputManager;
    private SharedPreferences sp;
    private View mEmotionLayout;
    private EditText mEditText;
    private View mContentView;
    private ViewPager pager;
    private LinearLayout linearLayout;
    private View eView;

    private EmotionInputDetector() {}

    public static EmotionInputDetector with(Activity activity) {
        EmotionInputDetector emotionInputDetector = new EmotionInputDetector();
        emotionInputDetector.mActivity = activity;
        emotionInputDetector.mInputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        emotionInputDetector.sp = activity.getSharedPreferences(SHARE_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return emotionInputDetector;
    }

    public EmotionInputDetector bindToContent(View contentView) {
        mContentView = contentView;
        mContentView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                LogUtils.d("xxxxxxxxx", "mContentView onLayoutChange left = " + left + ", top = " + top + ", right = " + right + ", bottom = " + bottom
                    + ", oldLeft = " + oldLeft + ", oldTop = " + oldTop + ", oldRight = " + oldRight + ", oldBottom = " + oldBottom);
                if (bottom == oldBottom) {
                    return;
                }
                if (bottom - oldBottom >= ScreenUtils.dip2px(mActivity, 150)) {
                    if (mListener != null){
                        mListener.onPanelHide();
                    }
                }
                else if (bottom - oldBottom <= -ScreenUtils.dip2px(mActivity, 150)){
                    if (mListener != null) {
                        mListener.onPanelShown();
                    }
                }
            }
        });
        return this;
    }

    public EmotionInputDetector bindToEditText(EditText editText) {
        mEditText = editText;
        mEditText.requestFocus();
        mEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP && mEmotionLayout.isShown()) {
                    lockContentHeight();
                    hideEmotionLayout(true);

                    mEditText.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            unlockContentHeightDelayed();
                        }
                    }, 200L);
                }
                return false;
            }
        });
        return this;
    }

    public EmotionInputDetector bindToEmotionButton(final View emotionButton) {
        emotionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer resoureId = (Integer) emotionButton.getTag();
                if (mEmotionLayout.isShown() && null != eView && eView.isShown() && resoureId == R.drawable.k_face) {
                    showEmotionLayout();
                } else if (mEmotionLayout.isShown()) {
                    lockContentHeight();
                    hideEmotionLayout(true);
                    unlockContentHeightDelayed();
                } else {
                    if (isSoftInputShown()) {
                        lockContentHeight();
                        showEmotionLayout();
                        unlockContentHeightDelayed();
                    } else {
                        showEmotionLayout();
                    }
                }
            }
        });
        return this;
    }

    public EmotionInputDetector bindToEmotionViewPager(ViewPager pager) {
        this.pager = pager;
        return this;
    }

    public EmotionInputDetector bindToEmotionLinearLayout(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;
        return this;
    }

    public EmotionInputDetector bindToChangView(View eView) {
        this.eView = eView;
        return this;
    }

    public EmotionInputDetector bindToChangButton(View emotionButton) {
        emotionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEmotionLayout.isShown() && null != pager && pager.isShown()) {
                    showChangYong();
                } else if (mEmotionLayout.isShown()) {
                    lockContentHeight();
                    hideChangYongLayout(true);
                    unlockContentHeightDelayed();
                } else {
                    if (isSoftInputShown()) {
                        lockContentHeight();
                        showChangYong();
                        unlockContentHeightDelayed();
                    } else {
                        showChangYong();
                    }
                }
            }
        });
        return this;
    }

    public EmotionInputDetector setEmotionView(View emotionView) {
        mEmotionLayout = emotionView;
        return this;
    }

    public EmotionInputDetector build(){
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        hideSoftInput();
        return this;
    }

    public boolean interceptBackPress() {
        if (mEmotionLayout.isShown()) {
            hideEmotionLayout(false);
            return true;
        }
        return false;
    }

    private void showEmotionLayout() {
        int softInputHeight = getSupportSoftInputHeight();
        if (softInputHeight <= 0) {
            softInputHeight = sp.getInt(SHARE_PREFERENCE_TAG, ScreenUtils.dip2px(mActivity, 290));
        }
        hideSoftInput();
        mEmotionLayout.getLayoutParams().height = softInputHeight;
        mEmotionLayout.setVisibility(View.VISIBLE);
        if (mListener != null) {
            mListener.onEmotionShown();
        }
        eView.setVisibility(View.GONE);
        pager.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
    }

    private void showChangYong() {
        int softInputHeight = getSupportSoftInputHeight();
        if (softInputHeight <= 0) {
            softInputHeight = sp.getInt(SHARE_PREFERENCE_TAG, ScreenUtils.dip2px(mActivity, 290));
        }
        hideSoftInput();
        mEmotionLayout.getLayoutParams().height = softInputHeight;
        mEmotionLayout.setVisibility(View.VISIBLE);
//        if (mListener != null) {
//            mListener.onEmotionShown();
//        }
        eView.setVisibility(View.VISIBLE);
        pager.setVisibility(View.GONE);
        linearLayout.setVisibility(View.GONE);
    }

    public void hideEmotionLayout(boolean showSoftInput) {
        if (mEmotionLayout.isShown()) {
            mEmotionLayout.setVisibility(View.GONE);
            if (showSoftInput) {
                showSoftInput();
            }
            if (mListener != null) {
                mListener.onEmotionHide();
            }
        }
    }

    public void hideChangYongLayout(boolean showSoftInput) {
        if (mEmotionLayout.isShown()) {
            mEmotionLayout.setVisibility(View.GONE);
            if (showSoftInput) {
                showSoftInput();
            }
//            if (mListener != null) {
//                mListener.onEmotionHide();
//            }
        }
    }

    private void lockContentHeight() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mContentView.getLayoutParams();
        params.height = mContentView.getHeight();
        params.weight = 0.0F;
    }

    private void unlockContentHeightDelayed() {
        mEditText.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((LinearLayout.LayoutParams) mContentView.getLayoutParams()).weight = 1.0F;
            }
        }, 200L);
    }

    private void showSoftInput() {
        mEditText.requestFocus();
        mEditText.post(new Runnable() {
            @Override
            public void run() {
                mInputManager.showSoftInput(mEditText, 0);
            }
        });
    }

    public void hideSoftInput() {
        mInputManager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }

    public boolean isSoftInputShown() {
        return getSupportSoftInputHeight() > 0;
    }

    private int getSupportSoftInputHeight() {
        Rect r = new Rect();
        mActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
        int screenHeight = mActivity.getWindow().getDecorView().getRootView().getHeight();
        int softInputHeight = screenHeight - r.bottom;
        if (Build.VERSION.SDK_INT >= 20) {
            // When SDK Level >= 20 (Android L), the softInputHeight will contain the height of softButtonsBar (if has)
            softInputHeight = softInputHeight - getSoftButtonsBarHeight();
        }
        if (softInputHeight < 0) {
            Log.w("EmotionInputDetector", "Warning: value of softInputHeight is below zero!");
        }
        if (softInputHeight > 0) {
            sp.edit().putInt(SHARE_PREFERENCE_TAG, softInputHeight).apply();
        }
        LogUtils.d(TAG, "getSupportSoftInputHeight softInputHeight = " + softInputHeight);
        return softInputHeight;
    }

    public static boolean isFullScreen(Context context) {
        // true 是手势，默认是 false
        // https://www.v2ex.com/t/470543
        return Settings.Global.getInt(context.getContentResolver(), getDeviceInfo(), 0) != 0;

    }
    /**
     * 获取设备信息（目前支持几大主流的全面屏手机，亲测华为、小米、oppo、魅族、vivo都可以）
     *
     * @return
     */
    public static String getDeviceInfo() {
        String brand = Build.BRAND;
        if(TextUtils.isEmpty(brand))
            return "navigationbar_is_min";

        if (brand.equalsIgnoreCase("HUAWEI")) {
            return "navigationbar_is_min";
        } else if (brand.equalsIgnoreCase("XIAOMI")) {
            return "force_fsg_nav_bar";
        } else if (brand.equalsIgnoreCase("VIVO")) {
            return "navigation_gesture_on";
        } else if (brand.equalsIgnoreCase("OPPO")) {
            return "navigation_gesture_on";
        } else {
            return "navigationbar_is_min";
        }
    }

    /**
     * 非全面屏下 虚拟按键是否打开
     * @param activity
     * @return
     */
    public static boolean isNavigationBarShown(Activity activity){
        //虚拟键的view,为空或者不可见时是隐藏状态
        View view  = activity.findViewById(android.R.id.navigationBarBackground);
        if(view == null){
            return false;
        }
        int visible = view.getVisibility();
        if(visible == View.GONE || visible == View.INVISIBLE){
            return false ;
        }else{
            return true;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private int getSoftButtonsBarHeight() {
        if (isFullScreen(mActivity)) {
            return 0;
        }
        if(isNavigationBarShown(mActivity)){
            int result = 0;
            int resourceId = mActivity.getResources().getIdentifier("navigation_bar_height","dimen", "android");
            if (resourceId > 0) {
                result = mActivity.getResources().getDimensionPixelSize(resourceId);
            }
            if (result == 0){
                DisplayMetrics metrics = new DisplayMetrics();
                mActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int usableHeight = metrics.heightPixels;
                mActivity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
                int realHeight = metrics.heightPixels;

                if (realHeight > usableHeight) {
                    result = realHeight - usableHeight;
                }
            }
            return result;
        }
        return 0;
    }

    private DetectorListener mListener;

    public void setmListener(DetectorListener mListener) {
        this.mListener = mListener;
    }

    public interface DetectorListener {
        void onEmotionShown();
        void onEmotionHide();
        void onPanelShown(); //表情或键盘显示
        void onPanelHide(); //表情或键盘隐藏
    }
}
