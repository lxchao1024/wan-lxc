package com.guagua.wan.base

import android.content.Context
import android.content.IntentFilter
import android.graphics.PixelFormat
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.guagua.guagua.widget.MultipleStatusView
import com.guagua.wan.R
import com.guagua.wan.constant.Constant
import com.guagua.wan.event.NetworkChangeEvent
import com.guagua.wan.receiver.NetworkChangeReceiver
import com.guagua.wan.utils.MyConWrapper
import com.guagua.wan.utils.Preference
import com.guagua.wan.utils.SettingUtil
import com.guagua.wan.view.GToolBar
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.AnkoLogger

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/5/28 10:55
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
abstract class BaseActivity: AppCompatActivity(), AnkoLogger, GToolBar.OnGGToolbarClickListener {

    protected var isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)

    protected var lang: Boolean by Preference(Constant.LANG_KEY, true)

    protected var hasNetwork: Boolean by Preference(Constant.HAS_NETWORK_KEY, true)

    protected var mNetworkChangeReceiver: NetworkChangeReceiver? = null

    protected var mThemeColor: Int = SettingUtil.getColor()

    protected var mLayoutStatusView: MultipleStatusView? = null

    protected lateinit var mTipView: View
    protected lateinit var mWindowManager: WindowManager
    protected lateinit var mLayoutParams: WindowManager.LayoutParams

    protected abstract fun attachLayoutRes(): Int

    abstract fun initData()

    abstract fun initView()

    abstract fun start()

    open fun useEventBus(): Boolean = true

    open fun enableNetworkTip(): Boolean = true

    protected var mButtonLeft: ImageButton? = null
    protected var mButtonRight: ImageButton? = null
    protected var mRightTitleView: TextView? = null
    protected var mToolbar: GToolBar? = null

    open fun doReConnected() {
        start()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(MyConWrapper.wrap(newBase, "en"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        super.onCreate(savedInstanceState)
        setContentView(attachLayoutRes())
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }

        initTipView()
        initView()
        initData()
        start()
        initListener()
    }

    override fun onResume() {
        val filter = IntentFilter()
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        mNetworkChangeReceiver = NetworkChangeReceiver()
        registerReceiver(mNetworkChangeReceiver, filter)
        super.onResume()
        initColor()
    }

    private fun initTipView() {
        mTipView = layoutInflater.inflate(R.layout.layout_network_tip, null)
        mWindowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        mLayoutParams = WindowManager.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            PixelFormat.TRANSLUCENT)
        mLayoutParams.gravity = Gravity.TOP
        mLayoutParams.x = 0
        mLayoutParams.y = 0
        mLayoutParams.windowAnimations = R.style.anim_float_view // add animations
    }

    open fun initColor() {

    }

    open fun initListener() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onNetworkChangeEvent(event: NetworkChangeEvent) {
        Log.e("BaseActivity", "onNetworkChangeEvent(), isConnected:" + event.isConnected)
        hasNetwork = event.isConnected
    }

    override fun onLeftBtnClick(v: View?) {
        finish()
    }

    override fun onRightBtnClick(v: View?) {
    }

    override fun onRightTextClick(v: View?) {
    }

    protected fun setGToolBar(bar: GToolBar) {
        this.mToolbar = bar
        mToolbar?.visibility = View.VISIBLE
        mToolbar?.setOnGGToolbarClickListener(this)
    }

    override fun setTitle(title: CharSequence?) {
        mToolbar?.title = title
    }

    override fun setTitle(titleId: Int) {
        mToolbar?.setTitle(titleId)
    }

    open fun setTitle(title: CharSequence?, isBold: Boolean) {
        mToolbar?.title = title
        mToolbar?.setTitleBold(isBold)
    }

    protected fun setRightBtnDrawable(resId: Int): ImageButton? {
        mToolbar?.let {
            mButtonRight = it.setRightBtnDrawable(resId)
            mButtonRight?.visibility = View.VISIBLE
            return@let
        }
        return null
    }

    protected fun setLeftBtnDrawable(resId: Int): ImageButton? {
        mToolbar?.let {
            mButtonLeft = it.setLeftBtnDrawable(resId)
            mButtonLeft!!.visibility = View.VISIBLE
            return@let
        }
        return null
    }

    override fun onPause() {
        if (mNetworkChangeReceiver != null) {
            unregisterReceiver(mNetworkChangeReceiver)
            mNetworkChangeReceiver = null
        }
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun finish() {
        super.finish()
    }
}