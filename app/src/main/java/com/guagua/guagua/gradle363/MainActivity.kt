package com.guagua.guagua.gradle363

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import coil.api.load
import coil.transform.CircleCropTransformation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.placeholder.*

class MainActivity: AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        content_plus.setOnClickListener(this)
        content_left.setOnClickListener(this)
        content_middle.setOnClickListener(this)
        content_right.setOnClickListener(this)

        content_bg_main.load("https://img-blog.csdnimg.cn/20200110125029839.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDIyOTAxMQ==,size_16,color_FFFFFF,t_70") {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
    }

    override fun onClick(v: View?) {
        placeholder_plus.setContentId(v!!.id)
        TransitionManager.beginDelayedTransition(constraintLayout, ChangeBounds().apply {
            interpolator = OvershootInterpolator()
            duration = 1000
        })
    }
}


fun ImageView.mLoad(url: String) {

}