package com.guagua.wan.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.guagua.wan.R

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/10 18:22
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
object ImageLoader {
    fun load(context: Context?, url: String?, iv: ImageView?) {
        iv?.apply {
            Glide.with(context!!).clear(iv)
            val options = RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA).placeholder(R.drawable.bg_placeholder)
            Glide.with(context!!).load(url).transition(DrawableTransitionOptions.withCrossFade()).apply(options).into(iv)
        }
    }
}