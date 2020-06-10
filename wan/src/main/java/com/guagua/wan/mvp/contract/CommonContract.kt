package com.guagua.wan.mvp.contract

import com.guagua.wan.base.IModel
import com.guagua.wan.base.IPresenter
import com.guagua.wan.base.IView
import com.guagua.wan.model.bean.HttpResult
import io.reactivex.Observable

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/10 13:37
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
interface CommonContract {
    interface View: IView {
        fun showCollectSuccess(success: Boolean)
        fun showCancelCollectSuccess(success: Boolean)
    }

    interface Presenter<in V: View>: IPresenter<V> {
        fun addCollectArticle(id: Int)
        fun cancelCollectArticle(id: Int)
    }

    interface Model: IModel {
        fun addCollectArticle(id: Int): Observable<HttpResult<Any>>
        fun cancelCollectArticle(id: Int): Observable<HttpResult<Any>>
    }
}