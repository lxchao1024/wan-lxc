package com.guagua.guagua.gradle363

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/5/19 15:18
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
class IMyInterfaceService : Service() {
    private var binder: MyBinder? = null
    override fun onBind(intent: Intent): IBinder? {
        if (null == binder) {
            binder = MyBinder()
        }
        return binder
    }
}