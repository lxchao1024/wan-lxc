package com.guagua.wan.http.exception

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/10 16:58
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
object ErrorStatus {
    /**
     * 响应成功
     */
    const val SUCCESS = 0

    /**
     * Token 过期
     */
    const val TOKEN_INVALID = 401

    /**
     * 未知错误
     */
    const val UNKNOWN_ERROR = 1002

    /**
     * 服务器内部错误
     */
    const val SERVER_ERROR = 1003

    /**
     * 网络连接超时
     */
    const val NETWORK_ERROR = 1004

    /**
     * API解析异常（或者第三方数据结构更改）等其他异常
     */
    const val API_ERROR = 1005
}