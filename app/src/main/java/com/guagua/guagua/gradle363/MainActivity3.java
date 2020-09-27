package com.guagua.guagua.gradle363;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.guagua.guagua.gradle363.view.AutoScrollTextView;

public class MainActivity3 extends AppCompatActivity {

    AutoScrollTextView autoScrollTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        //启动公告滚动条
        autoScrollTextView = findViewById(R.id.test);
//        autoScrollTextView.init(getWindowManager());
        autoScrollTextView.startScroll();
//        autoScrollTextView.startFor0();
    }
}