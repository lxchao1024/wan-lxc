package com.lxc.jetpack.lifecycles;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.lxc.jetpack.SLog;

/**
 * Copyright (C), 2020-2020, guagua
 * @author lxc
 * Date: 2020/9/27 10:41
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
public class MyObserver implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void connectListener() {
        SLog.d("invoke");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void disconnectListener() {
        SLog.d("invoke");
    }
}
