package com.guagua.wan.utils

import androidx.appcompat.widget.Toolbar
import com.guagua.wan.R
import org.jetbrains.anko.toast

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
    fun initMainToolBar() {
        toolBar.title = "呱呱影音"
        toolBar.inflateMenu(R.menu.setting)
        toolBar.setOnMenuItemClickListener {
            toolBar.context.toast("设置点击了")
            true
        }
    }
}