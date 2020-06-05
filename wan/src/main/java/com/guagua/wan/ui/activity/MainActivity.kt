package com.guagua.wan.ui.activity

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import com.google.android.material.navigation.NavigationView
import com.guagua.wan.R
import com.guagua.wan.base.BaseActivity
import com.guagua.wan.utils.FragmentUtil
import com.guagua.wan.utils.MyConWrapper
import com.guagua.wan.utils.SettingUtil
import com.guagua.wan.utils.ToolBarManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

class MainActivity: BaseActivity(), ToolBarManager {

    override val toolBar: Toolbar by lazy { find<Toolbar>(R.id.toolBar) }

    override fun attachLayoutRes(): Int = R.layout.activity_main

    override fun initData() {
        initMainToolBar()
        initDrawerLayout()
        initNavView()
    }

    override fun initView() {
        bottomBar.setOnTabSelectListener {
            FragmentUtil.instances.getFragment(it)?.let { fragment ->
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.container, fragment)
                transaction.commit()
            }
        }
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
            //动态修改item的title名称显示
            menu.findItem(R.id.nav_night_mode).title = if (SettingUtil.getIsNightMode()) getString(R.string.nav_night_mode_no) else getString(R.string.nav_night_mode)
            //动态隐藏某个item
            menu.findItem(R.id.nav_language).isVisible = false
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
            R.id.nav_language -> {
                MyConWrapper.wrap(this, "en")
                recreate()
            }
        }
        true
    }
}
