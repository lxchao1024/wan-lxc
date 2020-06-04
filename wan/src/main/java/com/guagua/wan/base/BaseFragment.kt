package com.guagua.wan.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.jetbrains.anko.AnkoLogger

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/4 17:53
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
abstract class BaseFragment: Fragment(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        initData()
    }

    open fun init() {

    }

    abstract fun initView(): View?


    open fun initListener() {

    }

    open fun initData() {

    }
}