package com.zhangtao.androidlearndemo.sixth_service_demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Service_First extends Service {
    private static final String TAG = "Service_First";

    private class ff extends Binder implements com.zhangtao.androidlearndemo.sixth_service_demo.bankDemo.Interfaces.ICommunication {

        @Override
        public void callServiceInnerMenthod() {
            sayHello();
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind...");
        return new ff();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate...");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand...");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind...");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy...");
        super.onDestroy();
    }

    public void sayHello(){
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
    }


}
