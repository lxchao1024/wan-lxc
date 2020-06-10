package com.guagua.wan.http

import com.guagua.wan.BuildConfig
import com.guagua.wan.api.ApiService
import com.guagua.wan.app.App
import com.guagua.wan.constant.Constant
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/10 17:04
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
object RetrofitHelper {
    private var retrofit: Retrofit? = null

    val service: ApiService by lazy { getRetrofit()!!.create(ApiService::class.java) }

    private fun getRetrofit(): Retrofit? {
        if (null == retrofit) {
            retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        return retrofit
    }

    const val MAX_CACHE_SIZE: Long = 1024 * 1024 * 50 // 50M 的缓存大小
    const val DEFAULT_TIMEOUT: Long = 15

    private fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        val httpLoggerInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggerInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggerInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        val cacheFile = File(App.context.cacheDir, "cache")
        val cache = Cache(cacheFile, MAX_CACHE_SIZE)

        builder.run {
            addInterceptor(httpLoggerInterceptor)
            cache(cache)
            connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
        }

        return builder.build()
    }
}