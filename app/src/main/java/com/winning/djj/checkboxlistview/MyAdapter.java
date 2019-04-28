package com.winning.djj.checkboxlistview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述: adapter
 * 作者|时间: djj on 2019/4/26 17:16
 * 博客地址: http://www.jianshu.com/u/dfbde65a03fc
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<String> list;
    private Context mContext;
    //用来记录所有checkbox的状态
    private Map<Integer, Boolean> checkStatus = new HashMap<>();

    public MyAdapter(Context mContext) {

        this.mContext = mContext;
        initData();
    }

    private void initData() {
        list = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            list.add("CheckBox" + i);
        }
        initCheck(false);
    }

    //全选
    public void selectAll() {
        initCheck(true);
        notifyDataSetChanged();
    }

    //全不选
    public void unSelectAll() {
        initCheck(false);
        notifyDataSetChanged();
    }

    //更改集合内部存储的状态
    public void initCheck(boolean flag) {
        for (int i = 0; i < list.size(); i++) {
            //更改指定位置的数据
            checkStatus.put(i, flag);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.checkBox.setText(list.get(position));
        //清除监听器
        holder.checkBox.setOnCheckedChangeListener(null);
        //设置选中状态
        holder.checkBox.setChecked(checkStatus.get(position));
        //再设置一次CheckBox的选中监听器，当CheckBox的选中状态发生改变时，把改变后的状态储存在Map中
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkStatus.put(position, isChecked);
                //check状态一旦改变，保存的check值也要发生相应的变化
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.check_box);
        }
    }
}
