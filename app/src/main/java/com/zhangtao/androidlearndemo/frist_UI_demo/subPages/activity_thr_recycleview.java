package com.zhangtao.androidlearndemo.frist_UI_demo.subPages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.zhangtao.androidlearndemo.R;
import com.zhangtao.androidlearndemo.frist_UI_demo.adapters.adapter_MyAdapter;


public class activity_thr_recycleview extends AppCompatActivity {
    RecyclerView rcvItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[][] showData = new String[][]{{"资源学院","选择"},{"风景园林学院","妖精"},{"农学院","牛逼"}};

        setContentView(R.layout.activity_thr_recycleview);
        rcvItems = findViewById(R.id.rcv_items);
        rcvItems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        rcvItems.setBackgroundColor(getColor(R.color.colorPrimary));
        rcvItems.setAdapter(new adapter_MyAdapter(this,null, showData));
    }

}
