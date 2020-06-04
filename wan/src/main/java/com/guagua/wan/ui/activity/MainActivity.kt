package com.guagua.wan.ui.activity

import androidx.appcompat.app.AppCompatDelegate
import com.guagua.wan.R
import com.guagua.wan.base.BaseActivity
import com.guagua.wan.utils.SettingUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: BaseActivity() {

    override fun attachLayoutRes(): Int = R.layout.activity_main

    override fun initData() {

    }

    override fun initView() {
        textView.setOnClickListener {
            if (SettingUtil.getIsNightMode()) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                SettingUtil.setIsNightMode(false)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                SettingUtil.setIsNightMode(true)
            }
        }
    }

    override fun start() {
    }
}
