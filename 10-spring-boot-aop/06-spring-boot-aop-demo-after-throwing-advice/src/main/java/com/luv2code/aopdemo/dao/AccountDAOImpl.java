package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO{

    private String name;
    private String serviceCode;

    public String getName() {
        System.out.println("in getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println("in setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println("getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println("setServiceCode()");
        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts() {
        return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {
        //simulate exception
        if(tripWire){
            throw new RuntimeException("EXCEPTION!");
        }
        List<Account> myAccounts = new ArrayList<>();

        Account account1 = new Account("John", "1");
        Account account2 = new Account("Steve", "2");
        Account account3 = new Account("Fabio", "3");

        myAccounts.add(account1);
        myAccounts.add(account2);
        myAccounts.add(account3);
        return myAccounts;
    }

    /*
    @Override
    public void addAccount() {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
    }
     */

    /*
    @Override
    public void addAccount(Account account) {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
    }
     */

    @Override
    public void addAccount(Account account, boolean vipFlag) {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + ": doWork()");
        return false;
    }
}
