package com.guagua.wan.ui.fragment

import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.guagua.wan.base.BaseFragment

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/4 17:59
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
class HomeFragment: BaseFragment() {
    override fun initView(): View? {
        val textView = TextView(activity)
        textView.gravity = Gravity.CENTER
        textView.text = javaClass.simpleName
        textView.textSize = 18.0f
        return textView
    }

    override fun initData() {
        super.initData()
    }
}