package com.zhangtao.androidlearndemo.frist_UI_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.TimePicker;
import android.widget.Toast;


import com.zhangtao.androidlearndemo.R;
import com.zhangtao.androidlearndemo.frist_UI_demo.adapters.adapter_MyAdapter;
import com.zhangtao.androidlearndemo.frist_UI_demo.subPages.activity_thr_multipleChoose;
import com.zhangtao.androidlearndemo.frist_UI_demo.subPages.activity_thr_recycleview;
import com.zhangtao.androidlearndemo.frist_UI_demo.subPages.activity_thr_singleChoose;

import java.util.Calendar;

public class activity_sec_ui extends AppCompatActivity implements View.OnClickListener {
    private  final String TAG= "activity_sec_ui";
    private Button btn_dataChoose,btn_timeChoose,btn_singleChoose,btn_multipleChoose, btnRecycleView;
    private Button btn_popupwindow;
//    private SlidingMenu slidingMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_ui);
        btn_dataChoose = findViewById(R.id.btn_datachoose);
        btn_timeChoose = findViewById(R.id.btn_timechoose);
        btn_singleChoose = findViewById(R.id.btn_singlechoose);
        btn_multipleChoose = findViewById(R.id.btn_multiplechoose);
        btn_dataChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取日历的一个对象
                Calendar calendar = Calendar.getInstance();
                //获取年月日时分的信息
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(v.getContext(),0,new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String res = String.format("你选择的日期是：%d-%d-%d", year, month+1, dayOfMonth);
                        Toast.makeText(view.getContext(), res,Toast.LENGTH_LONG).show();
                        Log.d(TAG, res);
                    }
                },year,month,day).show();

            }
        });
        btn_timeChoose.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  //获取日历的一个对象
                  Calendar calendar = Calendar.getInstance();
                  //获取年月日时分的信息
                  int hour = calendar.get(Calendar.HOUR_OF_DAY);
                  int mintue = calendar.get(Calendar.MINUTE);
                  new TimePickerDialog(v.getContext(),1,new TimePickerDialog.OnTimeSetListener() {
                      @Override
                      public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                          String res = String.format("你选择的时间是：%d:%d", hourOfDay,minute);
                          Toast.makeText(view.getContext(), res,Toast.LENGTH_LONG).show();
                          Log.d(TAG, res);
                      }
                  },hour,mintue,true).show();
              }
        });
        btn_singleChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_sec_ui.this, activity_thr_singleChoose.class);
                startActivity(intent);
            }
        });
        btn_multipleChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_sec_ui.this, activity_thr_multipleChoose.class);
                startActivity(intent);
            }
        });
        btnRecycleView = findViewById(R.id.btn_recycleview);
        btnRecycleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_sec_ui.this, activity_thr_recycleview.class);
                startActivity(intent);
            }
        });
        btn_popupwindow = findViewById(R.id.btn_pop_recycle);
        btn_popupwindow.setOnClickListener(this);
        //设置SlidingMenu
//        slidingMenu = new SlidingMenu(this);
//        slidingMenu.setMode(SlidingMenu.LEFT);
//        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//        slidingMenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
//        slidingMenu.setMenu(R.layout.activity_main);
    }

    /**
     * 设置PopupWindow
     */
    private void setPopupWindow() {
        PopupWindow popupWindow = new PopupWindow();
        popupWindow.setContentView(getView(popupWindow));
        popupWindow.setWidth(btn_popupwindow.getWidth());
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(btn_popupwindow);
    }

    private View getView(PopupWindow pw){
        String[][] showData = new String[][]{{"资源学院","选择"},{"风景园林学院","妖精"},{"农学院","牛逼"}};
        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(btn_popupwindow.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT));
        recyclerView.setAdapter(new adapter_MyAdapter(this, pw, showData));
        return recyclerView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            //点击popupwindows控件弹出
            case R.id.btn_pop_recycle:
                setPopupWindow();
                break;
        }
    }
}
