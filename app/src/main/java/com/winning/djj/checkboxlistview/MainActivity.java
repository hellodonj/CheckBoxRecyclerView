package com.winning.djj.checkboxlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 描述: 主界面
 * 作者|时间: djj on 2019/4/26 17:13
 * 博客地址: http://www.jianshu.com/u/dfbde65a03fc
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R.id.cb_all)
    CheckBox cbAll;

    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        myAdapter = new MyAdapter(MainActivity.this);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setAdapter(myAdapter);

        //设置外面CheckBox的选中监听器，把它的选中状态赋值给其他的所有CheckBox,然后更新RecyclerView的Adapter
        cbAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbAll.isChecked()) {
                    myAdapter.selectAll();
                } else {
                    myAdapter.unSelectAll();
                }
            }
        });
    }

}
