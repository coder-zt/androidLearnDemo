package com.zhangtao.androidlearndemo.sixth_service_demo.bankDemo.Interfaces;

public interface IBossAction  extends  IWorkerAction{
    //修改用户的金额
    void alertAccount(float money);
}
