package com.guagua.wan.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/10 15:58
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
abstract class BasePresenter<M: IModel, V: IView>: IPresenter<V>, LifecycleObserver {

    protected var mModel: M? = null
    protected var mView: V? = null

    private val isViewAttached: Boolean
        get() = mView != null

    private var mCompositeDisposable: CompositeDisposable? = null

    open fun createModel(): M? = null

    open fun useEventBus(): Boolean = false

    override fun attachView(mView: V) {
        this.mView = mView
        mModel = createModel()
        if (mView is LifecycleOwner) {
            (mView as LifecycleOwner).lifecycle.addObserver(this)
            if (null != mModel && mModel is LifecycleObserver) {
                (mView as LifecycleOwner).lifecycle.addObserver(mModel as LifecycleObserver)
            }
        }
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
    }

    override fun detachView() {
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        unDispose()
        mModel?.onDetach()
        mModel = null
        mView = null
        mCompositeDisposable = null
    }

    open fun checkViewAttached() {
        if (!isViewAttached) {
            throw MvpViewNotAttachedException()
        }
    }

    open fun addSubscription(disposable: Disposable?) {
        if (null == mCompositeDisposable) {
            mCompositeDisposable = CompositeDisposable()
        }
        disposable?.let { mCompositeDisposable?.add(it) }
    }

    private fun  unDispose() {
        mCompositeDisposable?.clear()
        mCompositeDisposable = null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner) {
        owner.lifecycle.removeObserver(this)
    }

    private class MvpViewNotAttachedException internal constructor() : RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter")
}