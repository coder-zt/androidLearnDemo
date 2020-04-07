package com.zhangtao.androidlearndemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhangtao.androidlearndemo.fifth_broadcast_receive.activity_sec_Broadcast;
import com.zhangtao.androidlearndemo.fourth_activity_demo.activity_sec_Activity;
import com.zhangtao.androidlearndemo.frist_UI_demo.activity_sec_ui;
import com.zhangtao.androidlearndemo.second_save_data.activity_sec_setdata;
import com.zhangtao.androidlearndemo.sixth_service_demo.activity_sec_Service;
import com.zhangtao.androidlearndemo.thrid_sqlite_database.activity_sec_Sqlite;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnP3,btn2_1,btnp4, btnp5, btnBroadcasst,btnService;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        btnP3 = findViewById(R.id.btn_p3);
        btnP3.setOnClickListener(this);
        btn2_1 = findViewById(R.id.btn_p2_1);
        btn2_1.setOnClickListener(this);
        btnp4 =findViewById(R.id.btn_sqlite);
        btnp4.setOnClickListener(this);
        btnp5 = findViewById(R.id.btn_activity);
        btnp5.setOnClickListener(this);
        btnBroadcasst = findViewById(R.id.btn_broadcast);
        btnBroadcasst.setOnClickListener(this);
        btnService = findViewById(R.id.btn_service);
        btnService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch(v.getId()){
            case R.id.btn_p3:
                intent = new Intent(MainActivity.this, activity_sec_setdata.class);
                startActivity(intent);
                break;
            case R.id.btn_p2_1:
                intent = new Intent(MainActivity.this, activity_sec_ui.class);
                startActivity(intent);
                break;
            case R.id.btn_sqlite:
                intent = new Intent(MainActivity.this, activity_sec_Sqlite.class);
                startActivity(intent);
                break;
            case R.id.btn_activity:
                intent = new Intent(MainActivity.this, activity_sec_Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_broadcast:
                intent = new Intent(MainActivity.this, activity_sec_Broadcast.class);
                startActivity(intent);
                break;
            case R.id.btn_service:
                intent = new Intent(MainActivity.this, activity_sec_Service.class);
                startActivity(intent);
                break;
        }
    }
}
