package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;

public interface AccountDAO {

    //void addAccount();

    //void addAccount(Account account);
    void addAccount(Account account, boolean vipFlag);

    boolean doWork();

    public String getName();
    public void setName(String name);
    public String getServiceCode();
    public void setServiceCode(String serviceCode);
}
