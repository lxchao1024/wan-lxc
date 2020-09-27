package com.guagua.guagua.gradle363

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.guagua.guagua.gradle363.widget.MarqueeView
import kotlinx.android.synthetic.main.activity_constraint.*


class ConstraintActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint)


//        val image = ImageView(this)
//        image.setImageResource(R.mipmap.ic_launcher)
//        marqueeView1.addViewInQueue(image)
//        val image1 = ImageView(this)
//        image1.setImageResource(R.mipmap.ic_launcher)
//        marqueeView1.addViewInQueue(image1)
//        val image2 = ImageView(this)
//        image2.setImageResource(R.mipmap.ic_launcher)
//        marqueeView1.addViewInQueue(image2)
//        val image3 = ImageView(this)
//        image3.setImageResource(R.mipmap.ic_launcher)
//        marqueeView1.addViewInQueue(image3)
//        marqueeView1.setScrollSpeed(8)
//        marqueeView1.setViewMargin(15) //间距
//
//        marqueeView1.startScroll()

        marqueeView11.setMarqueeText("让我掉下眼泪的，不止昨夜的酒 ")
//        marqueeView11.start()
//
//        switcher02.setText("暂无任何预警信息!     暂无任何预警信息!2        暂无任何预警信息!3          暂无任何预警信息!4    ")
//        switcher02.setTextColor(Color.BLACK);

        resume.setOnClickListener {
//            marquee1.resumeScroll()
            marquee2.resumeScroll()
//            marquee3.resumeScroll()
//            marquee4.resumeScroll()
        }

        pause.setOnClickListener {
            marquee1.pauseScroll()
            marquee2.pauseScroll()
            marquee3.pauseScroll()
            marquee4.pauseScroll()
        }

        restart.setOnClickListener {
            marquee1.startScroll()
            marquee2.startScroll()
            marquee3.startScroll()
            marquee4.startScroll()
        }

        stop.setOnClickListener {
            marquee1.stopScroll()
            marquee2.stopScroll()
            marquee3.stopScroll()
            marquee4.stopScroll()
        }
    }
}