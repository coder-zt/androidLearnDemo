package com.zhangtao.androidlearndemo.frist_UI_demo.subPages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.zhangtao.androidlearndemo.R;

public class activity_thr_multipleChoose extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private final String TAG = "activity_thr_multipleChoose";
    private CheckBox cb_a,cb_b,cb_c,cb_d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thr_multiplechoose);
        cb_a = findViewById(R.id.cb_a);
        cb_b = findViewById(R.id.cb_b);
        cb_c = findViewById(R.id.cb_c);
        cb_d = findViewById(R.id.cb_d);
        cb_a.setOnCheckedChangeListener(this);
        cb_b.setOnCheckedChangeListener(this);
        cb_c.setOnCheckedChangeListener(this);
        cb_d.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int i = 0;
        if(cb_a.isChecked())
            i++;
        if(cb_b.isChecked())
            i++;
        if(cb_c.isChecked())
            i++;
        if(cb_d.isChecked())
            i++;
        Log.d(TAG, ""+i);
        switch (i){
            case 1:
                Toast.makeText(buttonView.getContext(), "我可是多选题！", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(buttonView.getContext(), "两个你就满足了！", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(buttonView.getContext(), "看来你还不够成熟！", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(buttonView.getContext(), "小孩才做选择，我全要！", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
