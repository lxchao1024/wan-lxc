package com.guagua.wan.model.bean

import com.squareup.moshi.Json
import java.io.Serializable

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/4 11:26
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */

data class TodoBean(
    @Json(name = "id") val id: Int,
    @Json(name = "completeDate") val completeDate: String,
    @Json(name = "completeDateStr") val completeDateStr: String,
    @Json(name = "content") val content: String,
    @Json(name = "date") val date: Long,
    @Json(name = "dateStr") val dateStr: String,
    @Json(name = "status") val status: Int,
    @Json(name = "title") val title: String,
    @Json(name = "type") val type: Int,
    @Json(name = "userId") val userId: Int,
    @Json(name = "priority") val priority: Int
): Serializable