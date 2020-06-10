package com.guagua.wan.rx

import com.guagua.wan.rx.scheduler.BaseScheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/10 16:44
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
class IoMainScheduler<T>: BaseScheduler<T>(Schedulers.io(), AndroidSchedulers.mainThread())