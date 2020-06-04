package com.guagua.guagua.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.guagua.guagua.gradle363.IMyInterface;

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/5/19 15:28
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
public class MainActivity extends AppCompatActivity {

    private IMyInterface iMyInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                iMyInterface = IMyInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        Intent intent = new Intent("com.guagua.guagua.gradle363.IMyInterfaceService");
        // 注意在 Android 5.0以后，不能通过隐式 Intent 启动 service，必须制定包名
        intent.setPackage("com.guagua.guagua.gradle363");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != iMyInterface) {
                    try {
                        Log.d("MainActivity", ("onClick(), bookName:" + iMyInterface.getBookName()));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
