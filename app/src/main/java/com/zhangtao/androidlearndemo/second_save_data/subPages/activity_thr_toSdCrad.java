package com.zhangtao.androidlearndemo.second_save_data.subPages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhangtao.androidlearndemo.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import static android.os.Environment.MEDIA_MOUNTED;

public class activity_thr_toSdCrad extends AppCompatActivity {
    EditText userInfo,passWord;
    Button doIt,read,btn_check;
    TextView resultShow;
    File filePath = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thr_to_sd_crad);
        userInfo = findViewById(R.id.et_userinfo);
        passWord = findViewById(R.id.et_password);
        doIt = findViewById(R.id.btn_do);
        read = findViewById(R.id.btn_read);
        resultShow = findViewById(R.id.tv_result);
        btn_check = findViewById(R.id.btn_check);
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String state = Environment.getExternalStorageState();
             Log.d("Test", state);
             File file = Environment.getExternalStorageDirectory();
             String exPath ="SD卡存储路径：" + file.toString();
             if(state.equals(MEDIA_MOUNTED)){
                 resultShow.setText(exPath);
                 filePath =   file;
                 doIt.setEnabled(true);
             }
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readUserInfo(v);
            }
        });
        doIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryInfo(v);

            }
        });
    }

    private void queryInfo(View v) {
        Log.d("Test", "开始检查");
        File exF = Environment.getExternalStorageDirectory();
        long freeSize = exF.getFreeSpace();
        long totalSize = exF.getTotalSpace();
        String strFreeSize = Formatter.formatFileSize(v.getContext(),freeSize);
        String strTotalSize = Formatter.formatFileSize(v.getContext(),totalSize);
        String result = "SD卡信息："+ strFreeSize + "/" + strTotalSize;
        Log.d("Test", result);
        resultShow.setText(result);
    }

    //    读取用户存储的数据
    private void readUserInfo(View v) {
        try{
            FileInputStream in = this.openFileInput(filePath + "data.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String result = bufferedReader.readLine();
            resultShow.setText("result:"+result);
            Log.d("TEST","文件读取结果：" +result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
