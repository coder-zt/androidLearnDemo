package com.zhangtao.androidlearndemo.sixth_service_demo.bankDemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import androidx.annotation.Nullable;

import com.zhangtao.androidlearndemo.IMyAidlInterface;
import com.zhangtao.androidlearndemo.R;
import com.zhangtao.androidlearndemo.sixth_service_demo.bankDemo.impls.mWorkerActionImpl;

public class activity_worker_ui extends Activity{

    private IMyAidlInterface iMyAidlInterface;
    private boolean mIsBind;
    private activity_worker_ui.workerServiceConnection workerServiceConnection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_worker);
        //绑定服务
        toBindService();
    }

    private void toBindService() {
        Intent intent = new Intent();
        intent.setAction("com.zhangtao.androidlearndemo.WORKERACTION");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setPackage(this.getPackageName());
        workerServiceConnection = new workerServiceConnection();
        mIsBind = bindService(intent, workerServiceConnection, BIND_AUTO_CREATE);
    }


    public void putMoney(View view){
        try {
            iMyAidlInterface.putMoney(99.99f);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public  void getMoney(View view){
        try {
            iMyAidlInterface.getMoney();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    class workerServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iMyAidlInterface = mWorkerActionImpl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    protected void onDestroy() {
        if(mIsBind && workerServiceConnection != null){
            unbindService(workerServiceConnection);
            mIsBind = false;
            iMyAidlInterface = null;
        }
        super.onDestroy();
    }
}
