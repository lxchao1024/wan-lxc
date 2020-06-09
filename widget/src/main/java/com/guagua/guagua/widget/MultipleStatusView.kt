package com.guagua.guagua.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import java.util.*

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/9 14:50
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
class MultipleStatusView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var mEmptyView: View? = null
    private var mErrorView: View? = null
    private var mLoadingView: View? = null
    private var mNoNetworkView: View? = null
    private var mContentView: View? = null

    private var mEmptyViewResId = 0
    private var mErrorViewResId = 0
    private var mLoadingViewResId = 0
    private var mNoNetworkViewResId = 0
    private var mContentViewResId = 0

    private var mViewStatus = STATUS_CONTENT
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mOnRetryClickListener: OnClickListener? = null

    private val mOtherIds = ArrayList<Int>()

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.MultipleStatusView, defStyleAttr, 0)
        mEmptyViewResId = a.getResourceId(R.styleable.MultipleStatusView_emptyView, R.layout.empty_view)
        mErrorViewResId = a.getResourceId(R.styleable.MultipleStatusView_errorView, R.layout.error_view)
        mLoadingViewResId = a.getResourceId(R.styleable.MultipleStatusView_loadingView, R.layout.loading_view)
        mNoNetworkViewResId = a.getResourceId(R.styleable.MultipleStatusView_noNetworkView, R.layout.no_network_view)
        mContentViewResId = a.getResourceId(R.styleable.MultipleStatusView_contentView, NULL_RESOURCE_ID)
        a.recycle()
    }

    companion object {
        private val DEFAULT_LAYOUT_PARAMS = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
        const val STATUS_CONTENT = 0x00
        const val STATUS_LOADING = 0x01
        const val STATUS_EMPTY = 0x02
        const val STATUS_ERROR = 0x03
        const val STATUS_NO_NETWORK = 0x04

        private const val NULL_RESOURCE_ID = -1
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        showContent()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mEmptyView?.let { clear(it) }
        mLoadingView?.let { clear(it) }
        mEmptyView?.let { clear(it) }
        mNoNetworkView?.let { clear(it) }
        mOtherIds?.clear()
        if (null != mOnRetryClickListener) {
            mOnRetryClickListener = null
        }
    }

    public fun getViewStatus() : Int = mViewStatus

    public fun setOnRetryClickListener(listener: OnClickListener) {
        mOnRetryClickListener = listener
    }

    fun showEmpty() {
        showEmpty(mEmptyViewResId, DEFAULT_LAYOUT_PARAMS)
    }

    fun showEmpty(layoutId: Int, params: ViewGroup.LayoutParams) {
        showEmpty(mEmptyView?:mInflater.inflate(layoutId, null), params)
    }

    fun showEmpty(view: View?, layoutParams: ViewGroup.LayoutParams?) {
        checkNull(view, "Empty view is null.")
        checkNull(layoutParams, "Layout params is null.")
        mViewStatus = STATUS_EMPTY
        if (null == mEmptyView) {
            mEmptyView = view
            val emptyRetryView = mEmptyView!!.findViewById<View>(R.id.empty_retry_view)
            if (null != mOnRetryClickListener && null != emptyRetryView) {
                emptyRetryView.setOnClickListener(mOnRetryClickListener)
            }
            mOtherIds.add(mEmptyView!!.id)
            addView(mEmptyView, 0, layoutParams)
        }
        showViewById(mEmptyView!!.id)
    }

    fun showNoNetwork() {
        showNoNetwork(mNoNetworkViewResId, DEFAULT_LAYOUT_PARAMS)
    }

    fun showNoNetwork(layoutId: Int, layoutParams: ViewGroup.LayoutParams?) {
        showNoNetwork(if (null == mNoNetworkView) mInflater.inflate(layoutId, null) else mNoNetworkView, layoutParams)
    }

    fun showNoNetwork(view: View?, params: ViewGroup.LayoutParams?) {
        checkNull(view, "No network view is null.")
        checkNull(params, "Layout params is null.")
        mViewStatus = STATUS_NO_NETWORK
        if (null == mNoNetworkView) {
            mNoNetworkView = view
            val noNetworkRetryView = mNoNetworkView!!.findViewById<View>(R.id.no_network_retry_view)
            if (null != mOnRetryClickListener && null != noNetworkRetryView) {
                noNetworkRetryView.setOnClickListener(mOnRetryClickListener)
            }
            mOtherIds.add(mNoNetworkView!!.id)
            addView(mNoNetworkView, 0, layoutParams)
        }
        showViewById(mNoNetworkView!!.id)
    }

    fun showError() {
        showError(mErrorViewResId, DEFAULT_LAYOUT_PARAMS)
    }

    fun showError(layoutId: Int, layoutParams: ViewGroup.LayoutParams?) {
        showNoNetwork(if (null == mErrorView) mInflater.inflate(layoutId, null) else mErrorView, layoutParams)
    }

    fun showError(view: View?, params: ViewGroup.LayoutParams?) {
        checkNull(view, "No network view is null.")
        checkNull(params, "Layout params is null.")
        mViewStatus = STATUS_ERROR
        if (null == mErrorView) {
            mErrorView = view
            val errorView = mErrorView!!.findViewById<View>(R.id.no_network_retry_view)
            if (null != mOnRetryClickListener && null != errorView) {
                errorView.setOnClickListener(mOnRetryClickListener)
            }
            mOtherIds.add(mNoNetworkView!!.id)
            addView(mErrorView, 0, layoutParams)
        }
        showViewById(mErrorView!!.id)
    }

    fun showLoading() {
        showLoading(mLoadingViewResId, DEFAULT_LAYOUT_PARAMS)
    }

    fun showLoading(layoutId: Int, layoutParams: ViewGroup.LayoutParams?) {
        showLoading((if (null == mLoadingView) mInflater.inflate(layoutId, null) else mLoadingView)!!, layoutParams)
    }

    fun showLoading(view: View, layoutParams: ViewGroup.LayoutParams?) {
        checkNull(view, "Loading view is null.")
        checkNull(layoutParams, "Layout params is null.")
        mViewStatus = STATUS_LOADING
        if (null == mLoadingView) {
            mLoadingView = view
            mOtherIds.add(mLoadingView!!.id)
            addView(mLoadingView, 0, layoutParams)
        }
        showViewById(mLoadingView!!.id)
    }

    fun showContent() {
        mViewStatus = STATUS_CONTENT
        if (null == mContentView && mContentViewResId != NULL_RESOURCE_ID) {
            mContentView = mInflater.inflate(mContentViewResId, null)
            addView(mContentView, 0, DEFAULT_LAYOUT_PARAMS)
        }
        showContentView()
    }

    fun showContent(layoutId: Int, layoutParams: ViewGroup.LayoutParams?) {
        showContent(mInflater.inflate(layoutId, null), layoutParams)
    }

    fun showContent(view: View, layoutParams: ViewGroup.LayoutParams?) {
        checkNull(view, "Content view is null.")
        checkNull(layoutParams, "Layout params is null.")
        mViewStatus = STATUS_CONTENT
        mContentView?.let { clear(it) }
        mContentView = view
        addView(mContentView, 0, layoutParams)
        showViewById(mContentView!!.id)
    }

    private fun showContentView() {
        val childCount = childCount
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            view.visibility = if (mOtherIds.contains(view.id)) View.GONE else View.VISIBLE
        }
    }

    private fun showViewById(vId: Int) {
        val childCount = childCount
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            view.visibility = if (view.id == vId) View.VISIBLE else View.GONE
        }
    }

    private fun checkNull(o: Any?, hint: String) {
        if (null == o) {
            throw NullPointerException(hint)
        }
    }

    private fun clear(vararg views: View) {
        if (null == views) {
            return
        }
        try {
            for (view in views) {
                view?.let { removeView(it) }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}