package com.lxc.jetpack.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Copyright (C), 2020-2020, guagua
 *
 * @author lxc
 * Date: 2020/9/27 15:15
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
public class MyViewModel extends ViewModel {
    private MutableLiveData<String> mutableLiveData;

    public LiveData<String> getName() {
        if (null == mutableLiveData) {
            mutableLiveData = new MutableLiveData<>();
            addName();
        }
        return mutableLiveData;
    }

    public void addName() {
        mutableLiveData.postValue("hello world");
    }

    public void addName(String content) {
        mutableLiveData.postValue(content);
    }
}
