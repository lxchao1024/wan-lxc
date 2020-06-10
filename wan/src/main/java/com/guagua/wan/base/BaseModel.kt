package com.guagua.wan.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/10 15:32
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
abstract class BaseModel : IModel, LifecycleObserver {
    private var mCompositeDisposable: CompositeDisposable? = null

    override fun addDisposable(disposable: Disposable?) {
        if (null == mCompositeDisposable) {
            mCompositeDisposable = CompositeDisposable()
        }
        disposable?.let { mCompositeDisposable?.add(it) }
    }

    override fun onDetach() {
        unDispose()
    }

    private fun unDispose() {
        mCompositeDisposable?.clear()
        mCompositeDisposable = null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    internal fun onDestroy(owner: LifecycleOwner) {
        owner.lifecycle.removeObserver(this)
    }
}