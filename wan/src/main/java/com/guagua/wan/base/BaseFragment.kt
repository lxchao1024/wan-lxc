package com.guagua.wan.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.guagua.guagua.widget.MultipleStatusView
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

    protected var mLayoutStatusView: MultipleStatusView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(attachLayoutRes(), null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        mLayoutStatusView?.setOnRetryClickListener(mRetryClickListener)
        mLayoutStatusView?.showLoading()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        initData()
    }

    open fun init() {

    }

    abstract fun initView(view: View)


    open fun initListener() {

    }

    open fun initData() {

    }
    abstract fun attachLayoutRes(): Int

    abstract fun lazyLoad()

    open val mRetryClickListener = View.OnClickListener {
        lazyLoad()
    }
}