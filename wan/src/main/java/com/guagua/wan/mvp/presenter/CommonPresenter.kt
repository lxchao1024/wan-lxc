package com.guagua.wan.mvp.presenter

import com.guagua.wan.base.BasePresenter
import com.guagua.wan.mvp.contract.CommonContract

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/10 13:50
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
open class CommonPresenter<M: CommonContract.Model, V: CommonContract.View>: BasePresenter<M, V>(), CommonContract.Presenter<V> {
    override fun addCollectArticle(id: Int) {
    }

    override fun cancelCollectArticle(id: Int) {
    }
}