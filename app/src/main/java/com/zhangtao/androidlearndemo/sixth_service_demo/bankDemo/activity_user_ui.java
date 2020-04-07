package com.zhangtao.androidlearndemo.sixth_service_demo.bankDemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import androidx.annotation.Nullable;

import com.zhangtao.androidlearndemo.R;
import com.zhangtao.androidlearndemo.sixth_service_demo.bankDemo.Interfaces.IUserAction;

public class activity_user_ui extends Activity {

    private boolean mIsBind;
    private activity_user_ui.mUserConnection mUserConnection;
    private IUserAction iUserAction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_user);
        //绑定服务
        doBindService();
    }

    private void doBindService() {
        Intent intent = new Intent();
        intent.setAction("com.zhangtao.androidlearndemo.USERACTION");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setPackage(this.getPackageName());
        mUserConnection = new mUserConnection();
        mIsBind = bindService(intent, mUserConnection, BIND_AUTO_CREATE);
    }
    class mUserConnection implements ServiceConnection{
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iUserAction = (IUserAction)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mIsBind && mUserConnection != null){
            unbindService(mUserConnection);
            mIsBind = false;
            iUserAction = null;
        }
    }

    public void putMoney(View view){
        iUserAction.putMoney(99.99f);
    }
    public  void getMoney(View view){
        iUserAction.getMoney();
    }
    public  void loanMoney(View view){
        iUserAction.loanMoney();
    }
}
