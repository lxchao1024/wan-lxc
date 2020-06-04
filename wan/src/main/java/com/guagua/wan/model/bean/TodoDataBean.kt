package com.guagua.wan.model.bean

import com.chad.library.adapter.base.entity.SectionEntity

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/4 11:25
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
class TodoDataBean : SectionEntity<TodoBean> {
    constructor(todoBean: TodoBean) : super(todoBean)
    constructor(isHeader: Boolean, headerName: String) : super(isHeader, headerName)
}