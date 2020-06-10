package com.guagua.wan.base

import android.view.View
import org.jetbrains.anko.support.v4.toast

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/10 11:30
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
abstract class BaseMvpFragment<in V: IView, P: IPresenter<V>>: BaseFragment(), IView {

    protected var mPresenter: P? = null

    protected abstract fun createPresenter(): P

    override fun initView(view: View) {
        mPresenter = createPresenter()
        mPresenter?.attachView(this as V)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter?.detachView()
        this.mPresenter = null
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(errorMsg: String) {
        toast(errorMsg)
    }

    override fun showDefaultMsg(msg: String) {
        toast(msg)
    }

    override fun showMsg(msg: String) {
        toast(msg)
    }
}