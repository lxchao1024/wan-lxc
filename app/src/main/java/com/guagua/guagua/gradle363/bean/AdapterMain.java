package com.guagua.guagua.gradle363.bean;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/8/26 17:00
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
class AdapterMain extends BaseAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }


    private static ImageView imageView = null;

    public static ImageView test() {
        return imageView;
    }
}
