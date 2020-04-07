package com.zhangtao.androidlearndemo.sixth_service_demo.bankDemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.zhangtao.androidlearndemo.R;

public class activity_bank_ui extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_ui);
    }

    public void userClick(View view) {
        Intent intent = new Intent(this, activity_user_ui.class);
        startActivity(intent);
    }

    public void workClick(View view) {
        Intent intent = new Intent(this, activity_worker_ui.class);
        startActivity(intent);
    }

    public void bossClick(View view) {
        Intent intent = new Intent(this, activity_boss_ui.class);
        startActivity(intent);
    }
}
