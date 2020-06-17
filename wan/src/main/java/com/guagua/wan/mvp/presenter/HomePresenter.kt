package com.guagua.wan.mvp.presenter

import com.guagua.wan.ext.ss
import com.guagua.wan.mvp.contract.HomeContract
import com.guagua.wan.mvp.model.HomeModel

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/10 15:31
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
class HomePresenter : CommonPresenter<HomeContract.Model, HomeContract.View>(), HomeContract.Presenter {

    override fun createModel(): HomeContract.Model? = HomeModel()

    override fun requestBanner() {
        mModel?.requestBanner()?.ss(mModel, mView, true) {
            mView?.setBanner(it.data)
        }
    }

    override fun requestHomeData() {
        requestBanner()
        requestArticles(0)
    }

    override fun requestArticles(num: Int) {
        mModel?.requestArticles(num)?.ss(mModel, mView, false) {
            mView?.setArticles(it.data)
        }
    }

}