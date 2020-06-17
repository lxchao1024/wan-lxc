package com.guagua.wan.mvp.model

import com.guagua.wan.http.RetrofitHelper
import com.guagua.wan.model.bean.Article
import com.guagua.wan.model.bean.ArticleResponseBody
import com.guagua.wan.model.bean.Banner
import com.guagua.wan.model.bean.HttpResult
import com.guagua.wan.mvp.contract.HomeContract
import io.reactivex.Observable

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/10 16:22
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
class HomeModel : CommonModel(), HomeContract.Model {
    override fun requestBanner(): Observable<HttpResult<List<Banner>>> {
        return RetrofitHelper.service.getBanners()
    }

    override fun requestTopArticles(): Observable<HttpResult<MutableList<Article>>> {
        return RetrofitHelper.service.getTopArticles()
    }

    override fun requestArticles(num: Int): Observable<HttpResult<ArticleResponseBody>> {
        return RetrofitHelper.service.getArticles(num)
    }
}