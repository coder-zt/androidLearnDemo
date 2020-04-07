package com.zhangtao.androidlearndemo.sixth_service_demo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.zhangtao.androidlearndemo.R;
import com.zhangtao.androidlearndemo.sixth_service_demo.service.Service_First;
import com.zhangtao.androidlearndemo.sixth_service_demo.bankDemo.activity_bank_ui;
import com.zhangtao.androidlearndemo.sixth_service_demo.bankDemo.Interfaces.ICommunication;
import com.zhangtao.androidlearndemo.sixth_service_demo.music_demo.UI.activity_miusc_ui;

public class activity_sec_Service extends Activity {

    private boolean isBindService = false;
    private ICommunication iCommunication;
    private static final String TAG = "activity_sec_Service";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_service);
    }

    /**
     * 开启服务
     * @param view
     */
    public void startSerivce(View view) {
        Intent intent = new Intent(this, Service_First.class);
        startService(intent);
    }

    /**
     * 停止服务
     * @param view
     */
    public void stopSerivce(View view) {
        Intent intent = new Intent();
        intent.setClass(this, Service_First.class);
        stopService(intent);
    }

    /**
     * 绑定服务
     * @param view
     */
    public void bindSerivce(View view) {
        Intent intent = new Intent();
        intent.setClass(this, Service_First.class);
        isBindService = bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iCommunication = (ICommunication) service;
            Log.d(TAG,"连接服务。。。");

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iCommunication = null;
            Log.d(TAG,"断开服务。。。");
        }
    };
    /**
     * 解除服务
     * @param view
     */
    public void unbindSerivce(View view) {
        if(mServiceConnection != null && isBindService){
            unbindService(mServiceConnection);
           iCommunication = null;
        }
    }

    /**
     * 调用程序内部方法
     * @param view
     */
    public void callSerivceMethod(View view) {
        if(iCommunication != null){
            iCommunication.callServiceInnerMenthod();
        }
    }

    public void toBankActivity(View view) {
        Intent intent = new Intent(this, activity_bank_ui.class);
        startActivity(intent);
    }

    public void toMiuscActivity(View view) {
        Intent intent = new Intent(this, activity_miusc_ui.class);
        startActivity(intent);
    }
}
