package com.guagua.wan.mvp.model

import com.guagua.wan.base.BaseModel
import com.guagua.wan.http.RetrofitHelper
import com.guagua.wan.model.bean.HttpResult
import com.guagua.wan.mvp.contract.CommonContract
import io.reactivex.Observable

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/17 13:50
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
open class CommonModel: BaseModel(), CommonContract.Model  {
    override fun addCollectArticle(id: Int): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.addCollectArticle(id)
    }

    override fun cancelCollectArticle(id: Int): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.cancelCollectArticle(id)
    }
}