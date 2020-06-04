package com.guagua.wan.utils

import com.guagua.wan.R
import com.guagua.wan.base.BaseFragment
import com.guagua.wan.ui.fragment.*

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/4 18:04
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
class FragmentUtil private constructor(){
    private val homeFragment by lazy { HomeFragment() }
    private val mvFragment by lazy { MvFragment() }
    private val rankFragment by lazy { RankFragment() }
    private val meFragment by lazy { MeFragment() }

    companion object {
        val instances by lazy { FragmentUtil() }
    }

    fun getFragment(tabId: Int): BaseFragment? {
        when (tabId) {
            R.id.tab_home -> return homeFragment
            R.id.tab_mv -> return mvFragment
            R.id.tab_vbang -> return rankFragment
            R.id.tab_yuedan -> return meFragment
        }

        return null
    }
}