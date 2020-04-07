package com.zhangtao.androidlearndemo.fourth_activity_demo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zhangtao.androidlearndemo.R;
import com.zhangtao.androidlearndemo.fourth_activity_demo.subPages.activity_thr_intent;
import com.zhangtao.androidlearndemo.fourth_activity_demo.dataObject.data_user;

public class activity_sec_Activity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "activity_sec_Activity";
    private static final int REQUESR_CODE = 1;
    private static final int TAKEPHOTO_CODE = 2;
    private ImageView ivPhoto;
    private Button btnImplicit,btnExplicit,btnThridApp, btnSendData;
    private LinearLayout llView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_activity);
        initview();
        requestPermission();

    }

    private void initview() {
        btnExplicit = findViewById(R.id.btn_intentexplicit);
        btnExplicit.setOnClickListener(this);
        btnImplicit = findViewById(R.id.btn_intentimplicit);
        btnImplicit.setOnClickListener(this);
        btnThridApp = findViewById(R.id.btn_thrid_app);
        btnThridApp.setOnClickListener(this);
        btnSendData = findViewById(R.id.btn_send_data);
        btnSendData.setOnClickListener(this);
        ivPhoto = findViewById(R.id.iv_photo);
        llView = findViewById(R.id.ll_view);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch(v.getId()){
            case R.id.btn_intentexplicit:
                intent = new Intent(activity_sec_Activity.this, activity_thr_intent.class);
                intent.putExtra("mode", "显式");
                startActivity(intent);
                break;
            case R.id.btn_intentimplicit:
                intent = new Intent();
                intent.setAction("com.zhangtao.androidlearndemo.intent");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.putExtra("mode", "隐式");
                startActivity(intent);
                break;
            case R.id.btn_thrid_app:
                intent = new Intent();
                intent.setAction("android.intent.action.SEARCH");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setPackage("com.android.browser");
                startActivity(intent);
                break;
            case R.id.btn_send_data:
                intent = new Intent(activity_sec_Activity.this, activity_thr_intent.class);
                data_user user = new data_user();
                user.setAge(22);
                user.setName("张滔");
                user.setHeight(174);
                intent.putExtra("datainfo", user);
                startActivity(intent);
                break;
        }
    }

    /**
     * 动态申请相关权限
     */
    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA}, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void call(View v){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        Uri uri = Uri.parse("tel:10086");
        intent.setData(uri);
        startActivity(intent);
    }

    public void sendData(View v){
        Intent intent = new Intent();
        intent.setAction("com.zhangtao.androidlearndemo.intent");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        Uri uri = Uri.parse("info:信息内容");
        intent.setData(uri);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== REQUESR_CODE && resultCode == 2){
            Toast.makeText(this, data.getStringExtra("return"), Toast.LENGTH_SHORT).show();
        }
        if(requestCode == 2 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap mImageBitmap = (Bitmap) extras.get("data");
            llView.setBackground(new BitmapDrawable(mImageBitmap));
        }
    }

    public void returnData(View view) {
        Intent intent = new Intent(activity_sec_Activity.this, activity_thr_intent.class);
        intent.putExtra("mode", "数据回传");
        startActivityForResult(intent, REQUESR_CODE);
    }

    public void takePhoto(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivityForResult(intent, TAKEPHOTO_CODE);
    }
}
