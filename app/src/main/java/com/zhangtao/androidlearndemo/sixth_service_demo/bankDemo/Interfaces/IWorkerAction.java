package com.zhangtao.androidlearndemo.sixth_service_demo.bankDemo.Interfaces;

public interface IWorkerAction extends  IUserAction {
    //查用户信用
    void checkUserCredit();
    //冻结账户
    void freeze();
}
