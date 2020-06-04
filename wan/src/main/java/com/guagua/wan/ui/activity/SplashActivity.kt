package com.guagua.wan.ui.activity

import android.content.Intent
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import com.guagua.wan.R
import com.guagua.wan.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity(), ViewPropertyAnimatorListener {

    override fun attachLayoutRes(): Int = R.layout.activity_splash

    override fun initData() {
        initAnim()
    }

    override fun initView() {
    }

    override fun start() {
    }

    private fun initAnim() {
        ViewCompat.animate(imageView).scaleX(1.0f).scaleY(1.0f).setListener(this).duration=2000L
    }

    private fun jumpToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    override fun onAnimationEnd(view: View?) {
        jumpToMain()
    }

    override fun onAnimationCancel(view: View?) {
    }

    override fun onAnimationStart(view: View?) {
    }
}
