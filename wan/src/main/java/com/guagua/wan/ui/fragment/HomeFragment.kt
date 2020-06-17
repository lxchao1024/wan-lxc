package com.guagua.wan.ui.fragment

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.bingoogolapple.bgabanner.BGABanner
import cn.bingoogolapple.bgabanner.BGABanner.Delegate
import com.guagua.wan.R
import com.guagua.wan.adapter.HomeAdapter
import com.guagua.wan.base.BaseMvpFragment
import com.guagua.wan.model.bean.Article
import com.guagua.wan.model.bean.ArticleResponseBody
import com.guagua.wan.model.bean.Banner
import com.guagua.wan.mvp.contract.HomeContract
import com.guagua.wan.mvp.presenter.HomePresenter
import com.guagua.wan.utils.ImageLoader
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_home_banner.view.*
import org.jetbrains.anko.support.v4.toast

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/4 17:59
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
class HomeFragment: BaseMvpFragment<HomeContract.View, HomeContract.Presenter>(), HomeContract.View {

    private lateinit var banners: ArrayList<Banner>

    private val datas = mutableListOf<Article>()

    private var bannerView: View? = null

    override fun attachLayoutRes(): Int = R.layout.fragment_home

    override fun createPresenter(): HomeContract.Presenter = HomePresenter()

    private var homeAdapter: HomeAdapter? = null

    private var isInit = false

    /**
     * is Refresh
     */
    private var isRefresh = true

    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        isRefresh = true
        mPresenter?.requestHomeData()
    }

    override fun initView(view: View){
        super.initView(view)
        mLayoutStatusView = multipleView

        swipeRefreshLayout.run {
            setOnRefreshListener(onRefreshListener)
        }

        bannerView = layoutInflater.inflate(R.layout.item_home_banner, null)
        bannerView?.banner?.run {
            setDelegate(bannerDelegate)
        }

        isInit = true

        recyclerView.layoutManager = LinearLayoutManager(activity)
        homeAdapter = HomeAdapter(context, datas)
        homeAdapter?.run {
            addHeaderView(bannerView)
        }
        recyclerView.adapter = homeAdapter
    }

    override fun initData() {
        mPresenter?.requestHomeData()
    }

    override fun lazyLoad() {
        mLayoutStatusView?.showLoading()
        mPresenter?.requestHomeData()
    }


    override fun setBanner(result: List<Banner>) {
        banners = result as ArrayList<Banner>
        val urls = ArrayList<String>()
        val titles = ArrayList<String>()

        Observable.fromIterable(result).subscribe { list ->
            urls.add(list.imagePath)
            titles.add(list.title)
        }
        bannerView?.banner?.run {
            setAutoPlayAble(banners.size > 0)
            setData(urls, titles)
            setAdapter(bannerAdapter)
        }
        mLayoutStatusView?.showContent()
    }

    override fun scrollToTop() {

    }

    override fun setArticles(articles: ArticleResponseBody) {
        articles.datas.let {
            homeAdapter?.run {
                if (it.count() > 1) {
                    replaceData(it)
                } else {
                    replaceData(it)
                }
            }
        }
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showCollectSuccess(success: Boolean) {

    }

    override fun showCancelCollectSuccess(success: Boolean) {

    }

    private val bannerAdapter: BGABanner.Adapter<ImageView, String> by lazy {
        BGABanner.Adapter<ImageView, String> { _, imageView, feedImageUrl, _ ->
            ImageLoader.load(activity, feedImageUrl, imageView)
        }
    }

    private val bannerDelegate = Delegate<ImageView, String> { _, _, _, position ->
        if (banners.size > 0) {
            val data = banners[position]
            toast(data.title)
        }
    }
}