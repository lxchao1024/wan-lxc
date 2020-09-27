package com.lxc.jetpack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.lxc.jetpack.lifecycles.MyObserver
import com.lxc.jetpack.lifecycles.MyObserverJava
import com.lxc.jetpack.model.MyViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //lifecycle 被观察者
        lifecycle.addObserver(MyObserver())
        lifecycle.addObserver(MyObserverJava())

        //liveData
        var mutableLiveData = MutableLiveData<String>()

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
        val mutableLiveData1  = MutableLiveData<String>();
        val mutableLiveData2  = MutableLiveData<String>();
        val liveDataMerger = MediatorLiveData<String>();

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
