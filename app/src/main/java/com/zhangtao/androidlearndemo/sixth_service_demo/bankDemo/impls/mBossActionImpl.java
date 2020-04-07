package com.zhangtao.androidlearndemo.sixth_service_demo.bankDemo.impls;

import android.os.Binder;
import android.util.Log;

import com.zhangtao.androidlearndemo.sixth_service_demo.bankDemo.Interfaces.IBossAction;

public class mBossActionImpl extends Binder implements IBossAction {
    private static String TAG = "mBossActionImpl";
    @Override
    public void alertAccount(float money) {
        Log.d(TAG, "成功修改该客服的账户！");
    }


    @Override
    public void checkUserCredit() {
        Log.d(TAG, "你该客户的信誉良好！");
    }

    @Override
    public void freeze() {
        Log.d(TAG, "你成功冻结了该客户的账户元。");
    }



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
