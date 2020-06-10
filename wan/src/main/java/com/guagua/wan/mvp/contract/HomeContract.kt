package com.guagua.wan.mvp.contract

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

        fun setBanner(banners: List<Banner>)
    }

    interface Presenter: CommonContract.Presenter<View> {

        fun requestBanner()
    }

    interface Model: CommonContract.Model {
        fun requestBanner(): Observable<HttpResult<List<Banner>>>
    }
}