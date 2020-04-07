package com.zhangtao.androidlearndemo.sixth_service_demo.bankDemo.impls;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.zhangtao.androidlearndemo.IMyAidlInterface;

public class mWorkerActionImpl extends IMyAidlInterface.Stub {
    private static String TAG = "mWorkerActionImpl";
//    @Override
//    public void checkUserCredit() {
//        Log.d(TAG, "你该客户的信誉良好！");
//    }
//
//    @Override
//    public void freeze() {
//        Log.d(TAG, "你成功冻结了该客户的账户元。");
//    }
//
//
//
//    @Override
//    public void putMoney(float money) {
//        Log.d(TAG, "你已经存入了" + money + "元。");
//    }
//
//    @Override
//    public float getMoney() {
//        Log.d(TAG, "你取走了100元。");
//        return 0;
//    }
//
//    @Override
//    public float loanMoney() {
//        Log.d(TAG, "你已经贷款1000元。");
//        return 0;
//    }

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public void putMoney(float money) throws RemoteException {
        Log.d(TAG, "你已经存入了" + money + "元。");
    }

    @Override
    public float getMoney() throws RemoteException {

        return 100.0f;
    }

    @Override
    public IBinder asBinder() {
        return this;
    }
}
