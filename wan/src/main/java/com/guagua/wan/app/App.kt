package com.guagua.wan.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import com.guagua.wan.BuildConfig
import com.guagua.wan.utils.SettingUtil
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import org.litepal.LitePal
import kotlin.properties.Delegates

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/5/27 19:12
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
class App: Application() {

    private var refWatcher: RefWatcher? = null

    companion object {
        val TAG = "wan"
        var context: Context by Delegates.notNull()
        private set

        lateinit var instance: Application

        fun getRefWatcher(context: Context): RefWatcher? {
            val app = context.applicationContext as App
            return app.refWatcher
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
        instance = this
        refWatcher = setupLeakCanary()
        initConfig()
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
        initLitPal()
        initTheme()
    }

    private fun setupLeakCanary(): RefWatcher = if (LeakCanary.isInAnalyzerProcess(this)) { RefWatcher.DISABLED } else LeakCanary.install(this)

    private fun initConfig() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)
            .methodCount(0)
            .methodOffset(7)
            .tag(TAG)
            .build()
        Logger.addLogAdapter(object: AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    private fun initLitPal() {
        LitePal.initialize(this)
    }

    private fun initTheme() {
        Log.e(TAG, "isNightMode:${SettingUtil.getIsNightMode()}")
        if (SettingUtil.getIsNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private val mActivityLifecycleCallbacks = object: ActivityLifecycleCallbacks {
        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityStarted(activity: Activity) {
            Log.d(TAG, "onStart: " + activity.componentName.className)
        }

        override fun onActivityDestroyed(activity: Activity) {
            Log.d(TAG, "onDestroy: " + activity.componentName.className)
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        }

        override fun onActivityStopped(activity: Activity) {
        }

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            Log.d(TAG, "onCreated: " + activity.componentName.className)
        }

        override fun onActivityResumed(activity: Activity) {
        }

    }
}