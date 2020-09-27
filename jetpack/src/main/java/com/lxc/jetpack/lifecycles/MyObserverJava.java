package com.lxc.jetpack.lifecycles;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.lxc.jetpack.SLog;

/**
 * Copyright (C), 2020-2020, guagua
 * @author lxc
 * Date: 2020/9/27 10:53
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
public class MyObserverJava implements DefaultLifecycleObserver {
    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        SLog.d();
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        SLog.d();
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        SLog.d();
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        SLog.d();
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        SLog.d();
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        SLog.d();
    }
}
