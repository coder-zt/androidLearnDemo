package com.zhangtao.androidlearndemo.second_save_data.subPages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhangtao.androidlearndemo.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class activity_thr_writefile extends AppCompatActivity {
EditText userInfo,passWord;
Button doIt,read;
TextView resultShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thr_write_file);
        userInfo = findViewById(R.id.et_userinfo);
        passWord = findViewById(R.id.et_password);
        doIt = findViewById(R.id.btn_do);
        read = findViewById(R.id.btn_read);
        resultShow = findViewById(R.id.tv_result);
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readUserInfo(v);
            }
        });
        doIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInfo(v);
            }
        });
    }

//    读取用户存储的数据
    private void readUserInfo(View v) {
        try{
            FileInputStream in = this.openFileInput("data.text");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String result = bufferedReader.readLine();
            resultShow.setText("result:"+result);
            Log.d("TEST","文件读取结果：" +result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void saveUserInfo(View v) {
        String Info = this.userInfo.getText().toString();
        String psd = passWord.getText().toString();
        //检查输入合法性
//        if(Info.length() == 0){
//            Toast.makeText(this, "账号不得为空！", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if(psd.length() == 0){
//            Toast.makeText(this, "密码不得为空！", Toast.LENGTH_SHORT).show();
//            return;
//        }
//=================================================另一种实现方式：
       if( TextUtils.isEmpty(Info)){
           Toast.makeText(this, "账号不得为空！", Toast.LENGTH_SHORT).show();
           return;
        }
       if(TextUtils.isEmpty(psd)){
           Toast.makeText(this, "密码不得为空！", Toast.LENGTH_SHORT).show();
           return;
       }
            Log.d("TEST","写入文件路径："+ this.getFilesDir());
            File filePath = new File(this.getFilesDir(),"data.text");

        try{
            if(!filePath.exists()){
                filePath.createNewFile();
            }

            FileOutputStream out = new FileOutputStream(filePath);
            out.write((Info + "===" + psd).getBytes());
            out.close();
            Toast.makeText(this, "数据保存成功", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
