package com.guagua.wan.utils

import android.content.Intent
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.guagua.wan.R
import com.guagua.wan.ui.activity.SettingsActivity

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/4 15:11
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
interface ToolBarManager {
    val toolBar: Toolbar
    val toolbarTitle: TextView
    fun initMainToolBar() {
        toolBar.title = ""
        toolBar.inflateMenu(R.menu.setting)
        toolBar.setOnMenuItemClickListener {
            toolBar.context.startActivity(Intent(toolBar.context, SettingsActivity::class.java))
            true
        }
    }

    fun changeMainToolBarTitle(tabIndex: Int) {
        when (tabIndex) {
            R.id.tab_home -> toolbarTitle.text = "呱呱"
            R.id.tab_mv -> toolbarTitle.text = "MV"
            R.id.tab_vbang -> toolbarTitle.text = "排行"
            R.id.tab_yuedan -> toolbarTitle.text = "我的"
        }
    }
}