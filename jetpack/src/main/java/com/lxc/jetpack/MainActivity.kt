package com.lxc.jetpack

import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.Path
import android.os.Bundle
import android.view.View
import android.view.animation.PathInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginLeft
import androidx.lifecycle.*
import com.lxc.jetpack.lifecycles.MyObserver
import com.lxc.jetpack.lifecycles.MyObserverJava
import com.lxc.jetpack.model.MyViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val rootView by lazy {
        ConstraintLayout {
            layout_width = match_parent
            layout_height = match_parent
            background_color = "#fff000"

            TextView {
                layout_width = wrap_content
                layout_height = wrap_content
                text = "hello world"
                textSize = 12f
                setTextColor(Color.parseColor("#000000"))
                background_color = "#ff0000"
            }

            LinearLayout {
                orientation = android.widget.LinearLayout.VERTICAL
                layout_width = match_parent
                layout_height = match_parent
                background_color = "#00ff00"

                TextView {
                    layout_width = wrap_content
                    layout_height = wrap_content
                    text = "hello world"
                    textSize = 12f
                    setTextColor(Color.parseColor("#000000"))
                    background_color = "#ff0000"
                }
                TextView {
                    layout_width = wrap_content
                    layout_height = wrap_content
                    text = "hello world"
                    textSize = 22f
                    setTextColor(Color.parseColor("#000000"))
                    background_color = "#ff0000"
                }

                ImageView {
                    load("", {
                        scaleType = android.widget.ImageView.ScaleType.FIT_CENTER
                    })
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(rootView)
//        val path = Path().apply {
//            arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true)
//        }
//        ObjectAnimator.ofFloat(textView, "translationX", 100f, 200f).apply {
//            duration = 2000
////            interpolator = PathInterpolator(path)
//            start()
//        }
//        ObjectAnimator.ofFloat(textView, "translationY", -100f).apply {
//            duration = 2000
//            start()
//        }

        //lifecycle 被观察者
        lifecycle.addObserver(MyObserver())
        lifecycle.addObserver(MyObserverJava())

        //liveData
        val mutableLiveData = MutableLiveData<String>()

        mutableLiveData.observe(this, Observer<String> { t -> SLog.d("t: $t") })

        //更改LiveData中的数据
        val transformedLiveData = Transformations.map(mutableLiveData) { name -> "$name Android进阶解密"; }
        //监听数据改变
        transformedLiveData.observe(this, Observer { t -> SLog.d("after change str is : $t") })
        //postValue setValue须在主线程中使用
        mutableLiveData.postValue("Hello world!!")
        mutableLiveData.value = "Hello world!!!"
        mutableLiveData.postValue("Hello world!!@@")

        //合并多个LiveData数据源
        val mutableLiveData1 = MutableLiveData<String>()
        val mutableLiveData2 = MutableLiveData<String>()
        val liveDataMerger = MediatorLiveData<String>()

        liveDataMerger.addSource(mutableLiveData1) { result -> SLog.d("onChange1:$result") }
        liveDataMerger.addSource(mutableLiveData2) { result -> SLog.d("onChange2:$result") }
        liveDataMerger.observe(this, Observer { res -> SLog.d("liveDataMerge, OnChaged: $res") })

        mutableLiveData1.postValue("hello")
        mutableLiveData2.postValue("world")

        val model = ViewModelProviders.of(this).get(MyViewModel::class.java)
        model.name.observe(this, Observer<String> { t -> SLog.d("model result t: $t") })

        model.addName("this is a new Name")
    }
}
