package com.guagua.wan.ui.activity

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import com.google.android.material.navigation.NavigationView
import com.guagua.wan.R
import com.guagua.wan.base.BaseActivity
import com.guagua.wan.utils.SettingUtil
import com.guagua.wan.utils.ToolBarManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class MainActivity: BaseActivity(), ToolBarManager {

    override val toolBar: Toolbar by lazy { find<Toolbar>(R.id.toolBar) }

    override fun attachLayoutRes(): Int = R.layout.activity_main

    override fun initData() {
        initMainToolBar()
        initDrawerLayout()
        initNavView()
    }

    override fun initView() {
//        textView.setOnClickListener {
//            if (SettingUtil.getIsNightMode()) {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                SettingUtil.setIsNightMode(false)
//            } else {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                SettingUtil.setIsNightMode(true)
//            }
//        }
        bottomBar.setOnTabSelectListener {  }
    }

    override fun start() {
    }

    private fun initDrawerLayout() {
        drawerLayout.run {
            val toggle = ActionBarDrawerToggle(this@MainActivity, this, toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
            addDrawerListener(toggle)
            toggle.syncState()
        }
    }

    private fun initNavView() {
        navView.run {
            setNavigationItemSelectedListener(onDrawerNavigationItemSelectedListener)
        }
    }

    private val onDrawerNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId) {
            R.id.nav_night_mode -> {
                if (SettingUtil.getIsNightMode()) {
                    SettingUtil.setIsNightMode(false)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                } else {
                    SettingUtil.setIsNightMode(true)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
            }
        }
        true
    }
}
