package com.zhangtao.androidlearndemo.sixth_service_demo.bankDemo.impls;

import android.os.Binder;
import android.util.Log;

import com.zhangtao.androidlearndemo.sixth_service_demo.bankDemo.Interfaces.IUserAction;

public class mUserActionImpl extends Binder implements IUserAction {

    private static String TAG = " mUserActionImpl";

    @Override
    public void putMoney(float money) {
        Log.d(TAG, "你已经存入了" + money + "元。");
    }

    @Override
    public float getMoney() {
        Log.d(TAG, "你取走了100元。");
        return 0;
    }

    @Override
    public float loanMoney() {
        Log.d(TAG, "你已经贷款1000元。");
        return 0;
    }
}
