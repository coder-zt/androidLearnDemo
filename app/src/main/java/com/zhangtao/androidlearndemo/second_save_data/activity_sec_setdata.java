package com.zhangtao.androidlearndemo.second_save_data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhangtao.androidlearndemo.R;
import com.zhangtao.androidlearndemo.second_save_data.subPages.activity_thr_SharePreference;
import com.zhangtao.androidlearndemo.second_save_data.subPages.activity_thr_toSdCrad;
import com.zhangtao.androidlearndemo.second_save_data.subPages.activity_thr_writefile;

public class activity_sec_setdata extends AppCompatActivity implements View.OnClickListener {
private Button btnWrite,btnSdCard,btnSharePreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_setdata);
        btnWrite = findViewById(R.id.btn_writeFile);
        btnSdCard = findViewById(R.id.btn_to_sdcrad);
        btnSharePreference = findViewById(R.id.btn_sharepreference);
        btnSdCard.setOnClickListener(this);
        btnWrite.setOnClickListener(this);
        btnSharePreference.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch(v.getId()){
            case R.id.btn_writeFile:
                intent = new Intent(activity_sec_setdata.this, activity_thr_writefile.class);
                break;
            case R.id.btn_to_sdcrad:
                intent = new Intent(activity_sec_setdata.this, activity_thr_toSdCrad.class);
                break;
            case R.id.btn_sharepreference:
                intent = new Intent(activity_sec_setdata.this, activity_thr_SharePreference.class);
                break;
        }
        if(intent == null)
            return;
        startActivity(intent);
}



}
