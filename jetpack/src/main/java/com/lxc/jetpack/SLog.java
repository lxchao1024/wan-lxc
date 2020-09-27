package com.lxc.jetpack;

import android.util.Log;

/**
 * Copyright (C), 2020-2020, guagua
 * Date: 20-5-3 下午5:58
 * Version: 2.1.5
 * Description: 简单的日志类,自动增加TAG，代码行数，调用方法名
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 * @author pix
 */
public class SLog {
    public static void d() {
        StackTraceElement s = (new Throwable()).getStackTrace()[1];
        String t = s.getClassName().substring(s.getClassName().lastIndexOf(".") + 1);
        String m = s.getLineNumber() + ", " + s.getMethodName() + "()";
        Log.d(t, m);
    }

    public static void d(String msg) {
        StackTraceElement s = (new Throwable()).getStackTrace()[1];
        String t = s.getClassName().substring(s.getClassName().lastIndexOf(".") + 1);
        String m = s.getLineNumber() + ", " + s.getMethodName() + "(), " + msg;
        Log.d(t, m);
    }
}
