package com.guagua.wan.adapter;

import android.content.Context;
import android.text.style.AlignmentSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guagua.wan.R;

import java.util.List;

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/9/15 15:57
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    private Context context;
    private List<String> data;
    public MyAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.test_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return null == data ? 0 : data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        MyHolder(View itemView) {
            super(itemView);
        }
    }
}
