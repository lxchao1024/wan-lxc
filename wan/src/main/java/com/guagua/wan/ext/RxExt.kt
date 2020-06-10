package com.guagua.wan.ext

import android.util.Log
import com.guagua.wan.R
import com.guagua.wan.app.App
import com.guagua.wan.base.IModel
import com.guagua.wan.base.IView
import com.guagua.wan.http.exception.ErrorStatus
import com.guagua.wan.model.bean.BaseBean
import com.guagua.wan.rx.IoMainScheduler
import com.guagua.wan.utils.NetWorkUtil
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/10 16:40
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */

fun <T: BaseBean> Observable<T>.ss(model: IModel?, view: IView?, isShowLoading: Boolean = true, onSuccess: (T) -> Unit) {
    this.compose(IoMainScheduler<T>()).retry(3000L).subscribe(object: Observer<T> {
        override fun onComplete() {
            Log.d("HomeFragment", "override fun onComplete()")
            view?.hideLoading()
        }

        override fun onSubscribe(d: Disposable) {
            if (isShowLoading) view?.showLoading()
            model?.addDisposable(d)
            if (!NetWorkUtil.isNetworkConnected(App.context)) {
                view?.showDefaultMsg(App.instance.resources.getString(R.string.network_unavailable_tip))
                onComplete()
            }
        }

        override fun onNext(t: T) {
            when {
                t.errorCode == ErrorStatus.SUCCESS -> onSuccess.invoke(t)
                t.errorCode == ErrorStatus.TOKEN_INVALID -> {}
                else -> view?.showDefaultMsg(t.errorMsg)
            }
        }

        override fun onError(e: Throwable) {
            view?.hideLoading()
            view?.showError(e.message?:"Hello, world")
        }

    })
}