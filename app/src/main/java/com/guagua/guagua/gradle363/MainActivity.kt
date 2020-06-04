package com.guagua.guagua.gradle363

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View
import android.view.animation.OvershootInterpolator
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
    }

    override fun onClick(v: View?) {
        placeholder_plus.setContentId(v!!.id)
        TransitionManager.beginDelayedTransition(constraintLayout, ChangeBounds().apply {
            interpolator = OvershootInterpolator()
            duration = 1000
        })
    }
}
