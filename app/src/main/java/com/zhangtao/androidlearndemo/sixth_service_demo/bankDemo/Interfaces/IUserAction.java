package com.zhangtao.androidlearndemo.sixth_service_demo.bankDemo.Interfaces;

public interface IUserAction {
    //存钱
    void putMoney(float money);
    //取钱
    float getMoney();
    //贷款
    float loanMoney();
}
