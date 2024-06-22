package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;

import java.util.List;

public interface AccountDAO {

    //void addAccount();

    //void addAccount(Account account);
    void addAccount(Account account, boolean vipFlag);

    boolean doWork();

    String getName();
    void setName(String name);
    String getServiceCode();
    void setServiceCode(String serviceCode);

    List<Account> findAccounts();

    List<Account> findAccounts(boolean tripWire);
}
