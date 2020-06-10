package com.guagua.wan.mvp.model

import com.guagua.wan.http.RetrofitHelper
import com.guagua.wan.model.bean.Banner
import com.guagua.wan.model.bean.HttpResult
import com.guagua.wan.mvp.contract.HomeContract
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/10 16:22
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
class HomeModel : HomeContract.Model {
    override fun requestBanner(): Observable<HttpResult<List<Banner>>> {
        return RetrofitHelper.service.getBanners()
    }

    override fun addCollectArticle(id: Int): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.getBanners1()
    }

    override fun cancelCollectArticle(id: Int): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.getBanners1()
    }

    override fun addDisposable(disposable: Disposable?) {
    }

    override fun onDetach() {
    }
}