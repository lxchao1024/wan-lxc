package com.guagua.guagua.gradle363

import android.os.RemoteException
import android.util.Log
import java.util.*

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/5/19 15:16
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
class MyBinder : IMyInterface.Stub() {
    @Throws(RemoteException::class)
    override fun getBookName(): String {
        Log.d("MyBinder", "getBookName(),name:钢铁是怎样炼成的")
        return "getBookName(),name:钢铁是怎样炼成的" + Random().nextInt(1000)
    }
}