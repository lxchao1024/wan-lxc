package com.guagua.wan.api

import com.guagua.wan.model.bean.Article
import com.guagua.wan.model.bean.ArticleResponseBody
import com.guagua.wan.model.bean.Banner
import com.guagua.wan.model.bean.HttpResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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

    /**
     * 收藏站内文章
     * http://www.wanandroid.com/lg/collect/1165/json
     * @param id article id
     */
    @POST("lg/collect/{id}/json")
    fun addCollectArticle(@Path("id") id: Int): Observable<HttpResult<Any>>

    /**
     * 文章列表中取消收藏文章
     * http://www.wanandroid.com/lg/uncollect_originId/2333/json
     * @param id
     */
    @POST("lg/uncollect_originId/{id}/json")
    fun cancelCollectArticle(@Path("id") id: Int): Observable<HttpResult<Any>>

    @GET("article/top/json")
    fun getTopArticles(): Observable<HttpResult<MutableList<Article>>>

    @GET("article/list/{pageNum}/json")
    fun getArticles(@Path("pageNum") num: Int): Observable<HttpResult<ArticleResponseBody>>
}