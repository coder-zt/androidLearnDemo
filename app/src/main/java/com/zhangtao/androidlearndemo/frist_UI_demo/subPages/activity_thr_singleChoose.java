package com.zhangtao.androidlearndemo.frist_UI_demo.subPages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.zhangtao.androidlearndemo.R;

public class activity_thr_singleChoose extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private Button btn_submit;
    private RadioButton rb_a,rb_b,rb_c,rb_d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thr_singlechoose);
        btn_submit = findViewById(R.id.btn_submit);
        rb_a = findViewById(R.id.rb_a);
        rb_b = findViewById(R.id.rb_b);
        rb_c = findViewById(R.id.rb_c);
        rb_d = findViewById(R.id.rb_d);
        rb_a.setOnCheckedChangeListener(this);
        rb_b.setOnCheckedChangeListener(this);
        rb_c.setOnCheckedChangeListener(this);
        rb_d.setOnCheckedChangeListener(this);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String res = "";
                if(rb_a.isChecked()){
                    res = "咋不听呢！";
                }else if(rb_b.isChecked()){
                    res = "已经叫你选C了！";
                }else if(rb_c.isChecked()){
                    res = "你只会听B的吗？";
                }else if(rb_d.isChecked()){
                    res = "看来你没本事";
                }
                Toast.makeText(v.getContext(), res, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String res = "";
        switch(buttonView.getId()){
            case R.id.rb_a:
                res = "咋不听呢！";
                break;
            case R.id.rb_b:
                res =  "已经叫你选C了！";
                break;
            case R.id.rb_c:
                res = "你只会听B的吗？";
                break;
            case R.id.rb_d:
                res =  "看来你没本事";
                break;
        }
        Toast.makeText(buttonView.getContext(), res, Toast.LENGTH_SHORT).show();
    }
}
