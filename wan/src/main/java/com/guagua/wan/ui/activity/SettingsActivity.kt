package com.guagua.wan.ui.activity

import com.guagua.wan.R
import com.guagua.wan.base.BaseActivity
import kotlinx.android.synthetic.main.activity_settings.*

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/8 19:18
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
class SettingsActivity: BaseActivity() {
    override fun attachLayoutRes(): Int = R.layout.activity_settings

    override fun initData() {
    }

    override fun initView() {
        setGToolBar(toolBar)
        setLeftBtnDrawable(R.drawable.qiju_li_btn_back)
    }

    override fun start() {
    }
}