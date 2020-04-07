package com.zhangtao.androidlearndemo.fourth_activity_demo.subPages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhangtao.androidlearndemo.R;
import com.zhangtao.androidlearndemo.fourth_activity_demo.dataObject.data_user;

public class activity_thr_intent extends AppCompatActivity {
    private TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thr_intent);
        tvResult = findViewById(R.id.tv_result);
        Intent intent = getIntent();
        String result = intent.getStringExtra("mode");
        if(result == null){
            data_user user = intent.getParcelableExtra("datainfo");
            if(user == null){
                result = intent.getData().toString().split(":")[1];
            }else{
                result = "name===" + user.getName();
                result += "\nage===" + user.getAge();
                result += "\nheight===" + user.getHeight();
            }
        }else if( result.equals("数据回传")){
            returnData();
        }
        tvResult.setText(result);
    }

    private void returnData() {
        tvResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("return", "回传数据");
                setResult(2, intent);
                finish();
            }
        });
    }
}
