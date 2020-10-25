package com.guagua.guagua.gradle363;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.guagua.guagua.gradle363.utils.EmotionInputDetector;
import com.guagua.guagua.gradle363.view.AutoScrollTextView;

public class MainActivity3 extends AppCompatActivity {

    AutoScrollTextView autoScrollTextView;

    //表情键盘切换处理
    private EmotionInputDetector emotionInputDetector;



    private LinearLayout mLayoutBottom; //底部根布局
    private ImageView mIvFace;
    private EditText mEditChat;
    private ImageButton mBtnGift;
    private Button mBtnSend;
    private Button changyong;
    private ViewGroup mFaceLayout;
    private ViewPager mFaceViewPager;
    private LinearLayout mFaceIndexView;
    private ViewPager faceViewPager;
    private LinearLayout faceIndexView;
    private View changyongView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);



        mLayoutBottom = findViewById(R.id.layout_bottom);
        mIvFace = findViewById(R.id.iv_face);
        mEditChat = findViewById(R.id.edit_chat_content);
        mBtnGift = findViewById(R.id.btn_show_gift_out);
        mFaceLayout = findViewById(R.id.layout_face);
        mFaceViewPager = findViewById(R.id.faceViewPager);
        mFaceIndexView = findViewById(R.id.faceIndexView);
        changyong = findViewById(R.id.changyong);
        faceViewPager = findViewById(R.id.faceViewPager);
        changyongView = findViewById(R.id.changyongView);
        faceIndexView = findViewById(R.id.faceIndexView);
        View content = findViewById(R.id.contentList);
        mIvFace.setTag(R.drawable.k_face);

        //设置表情、键盘切换处理
        emotionInputDetector = EmotionInputDetector.with(MainActivity3.this);
        emotionInputDetector.setEmotionView(mFaceLayout)
                .bindToContent(content)
                .bindToEditText(mEditChat)
                .bindToEmotionButton(mIvFace)
                .bindToEmotionViewPager(faceViewPager)
                .bindToEmotionLinearLayout(faceIndexView)
                .bindToChangButton(changyong)
                .bindToChangView(changyongView)
                .build();
        emotionInputDetector.setmListener(new EmotionInputDetector.DetectorListener() {
            @Override
            public void onEmotionShown() {
                mIvFace.setTag(R.drawable.keyborad);
                mIvFace.setImageResource(R.drawable.keyborad);
            }

            @Override
            public void onEmotionHide() {
                mIvFace.setTag(R.drawable.k_face);
                mIvFace.setImageResource(R.drawable.k_face);
            }

            @Override
            public void onPanelShown() {
                smoothlyShowBtnSend();
            }

            @Override
            public void onPanelHide() {
                smoothlyHideBtnSend();
            }
        });

        //启动公告滚动条
//        autoScrollTextView = findViewById(R.id.test);
//        autoScrollTextView.init(getWindowManager());
//        autoScrollTextView.startScroll();
//        autoScrollTextView.startFor0();
    }

    private void smoothlyShowBtnSend() {

    }

    private void smoothlyHideBtnSend() {

    }
}