package com.guagua.wan.utils

import android.graphics.Color
import android.preference.PreferenceManager
import androidx.core.graphics.alpha
import com.guagua.wan.R
import com.guagua.wan.app.App

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/5/27 20:08
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
object SettingUtil {
    private val setting = PreferenceManager.getDefaultSharedPreferences(App.context)

    fun getIsNoPhotoMode(): Boolean {
        return setting.getBoolean("switch_noPhotoMode", false)
    }

    fun getIsShowTopArticle(): Boolean {
        return setting.getBoolean("switch_show_top", false)
    }

    fun getColor(): Int {
        val defaultColor = App.context.resources.getColor(R.color.colorPrimary)
        val color = setting.getInt("color", defaultColor)
        return if (color != 0 && Color.alpha(color) != 255) {
            defaultColor
        } else color
    }

    fun setColor(color: Int) {
        setting.edit().putInt("color", color).apply()
    }

    fun getNavBar(): Boolean {
        return setting.getBoolean("nav_bar", false)
    }

    fun setIsNightMode(flag: Boolean) {
        setting.edit().putBoolean("switch_nightMode", flag).apply()
    }

    fun getIsNightMode(): Boolean {
        return setting.getBoolean("switch_nightMode", false)
    }
}