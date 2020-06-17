package com.guagua.wan.mvp.contract

import com.guagua.wan.model.bean.Article
import com.guagua.wan.model.bean.ArticleResponseBody
import com.guagua.wan.model.bean.Banner
import com.guagua.wan.model.bean.HttpResult
import io.reactivex.Observable

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/10 13:37
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
interface HomeContract {
    interface View: CommonContract.View {
        //
        fun setBanner(banners: List<Banner>)

        fun scrollToTop()

        fun setArticles(articles: ArticleResponseBody)
    }

    interface Presenter: CommonContract.Presenter<View> {
        fun requestBanner()
        fun requestHomeData()
        fun requestArticles(num: Int)
    }

    interface Model: CommonContract.Model {
        fun requestBanner(): Observable<HttpResult<List<Banner>>>
        fun requestTopArticles(): Observable<HttpResult<MutableList<Article>>>
        fun requestArticles(num: Int): Observable<HttpResult<ArticleResponseBody>>
    }
}