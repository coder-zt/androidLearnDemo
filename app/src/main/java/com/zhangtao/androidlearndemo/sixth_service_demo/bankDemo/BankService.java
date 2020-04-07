package com.zhangtao.androidlearndemo.sixth_service_demo.bankDemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.zhangtao.androidlearndemo.sixth_service_demo.bankDemo.impls.mBossActionImpl;
import com.zhangtao.androidlearndemo.sixth_service_demo.bankDemo.impls.mUserActionImpl;
import com.zhangtao.androidlearndemo.sixth_service_demo.bankDemo.impls.mWorkerActionImpl;

public class BankService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        String action = intent.getAction();
        if(action.equals("com.zhangtao.androidlearndemo.USERACTION")){
            return new mUserActionImpl();
        }else if(action.equals("com.zhangtao.androidlearndemo.WORKERACTION")){
            return new mWorkerActionImpl();
        }else if(action.equals("com.zhangtao.androidlearndemo.BOSSACTION")){
            return new mBossActionImpl();
        }
        return null;
    }
}
