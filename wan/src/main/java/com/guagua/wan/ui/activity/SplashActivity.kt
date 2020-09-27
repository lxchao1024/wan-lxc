package com.guagua.wan.ui.activity

import android.content.Intent
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guagua.wan.R
import com.guagua.wan.adapter.MyAdapter
import com.guagua.wan.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity(), ViewPropertyAnimatorListener {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: MyAdapter
    private var list = arrayListOf<String>()

    override fun attachLayoutRes(): Int = R.layout.activity_splash

    override fun initData() {
//        initAnim()

        adapter = MyAdapter(this, list)
        linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.stackFromEnd = true
        linearLayoutManager.scrollToPositionWithOffset(adapter.itemCount - 1, Int.MAX_VALUE)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = linearLayoutManager


        list.add("a")
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
