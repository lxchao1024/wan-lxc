package com.guagua.wan.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.guagua.wan.constant.Constant
import com.guagua.wan.event.NetworkChangeEvent
import com.guagua.wan.utils.NetWorkUtil
import com.guagua.wan.utils.Preference
import org.greenrobot.eventbus.EventBus

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/5/28 10:56
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
class NetworkChangeReceiver : BroadcastReceiver() {

    private var hasNetwork: Boolean by Preference(Constant.HAS_NETWORK_KEY, true)

    override fun onReceive(context: Context, intent: Intent?) {
        val isConnected = NetWorkUtil.isNetworkConnected(context)
        if (isConnected) {
            if (hasNetwork != isConnected) {
                EventBus.getDefault().post(NetworkChangeEvent(isConnected))
            }
        } else {
            EventBus.getDefault().post(NetworkChangeEvent(isConnected))
        }
    }
}