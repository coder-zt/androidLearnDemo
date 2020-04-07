package com.zhangtao.androidlearndemo.second_save_data.subPages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.zhangtao.androidlearndemo.R;

public class activity_thr_SharePreference extends AppCompatActivity {
    final String TAG = "SharePreference";
    private Switch sShare;
    //创建sharedpreference对象
    SharedPreferences mSharePreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thr__share_preference);
        //设置名字和模式
        mSharePreference = this.getSharedPreferences("setting_info", MODE_PRIVATE);
        //获取指定key的值
        boolean check = mSharePreference.getBoolean("set_Share",false);
        sShare = findViewById(R.id.s_share);
        sShare.setChecked(check);
        sShare.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               setSharedPreference(isChecked);
            }
        });
    }

    private void setSharedPreference(boolean isChecked) {
        //获取该对象的编辑对象
        SharedPreferences.Editor editor = mSharePreference.edit();
        //编辑该对象
        editor.putBoolean("set_Share", isChecked);
        //编辑完成后提交，保存数据
        editor.commit();
    }
}
