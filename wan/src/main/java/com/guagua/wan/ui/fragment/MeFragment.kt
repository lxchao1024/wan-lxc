package com.guagua.wan.ui.fragment

import android.os.Handler
import android.view.View
import com.guagua.wan.R
import com.guagua.wan.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_me.*
import org.jetbrains.anko.support.v4.toast

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/4 17:59
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
class MeFragment: BaseFragment() {
    override fun initView(view: View) {
        mLayoutStatusView = multipleView
        Handler().postDelayed({
            mLayoutStatusView?.showNoNetwork()
        }, 3000L)
    }

    override fun attachLayoutRes(): Int = R.layout.fragment_me

    override fun lazyLoad() {
        toast("正在重试")
    }

}