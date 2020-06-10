package com.guagua.wan.api

import com.guagua.wan.model.bean.Banner
import com.guagua.wan.model.bean.HttpResult
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/4 11:23
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
interface ApiService {
    @GET("banner/json")
    fun getBanners(): Observable<HttpResult<List<Banner>>>

    @GET("banner/json")
    fun getBanners1(): Observable<HttpResult<Any>>
}