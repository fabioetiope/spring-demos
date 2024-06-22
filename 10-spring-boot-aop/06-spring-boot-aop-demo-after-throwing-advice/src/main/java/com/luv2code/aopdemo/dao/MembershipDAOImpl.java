package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO{

    @Override
    public void addAccount() {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING A MEMBERSHIP");
    }

    @Override
    public void addAccount2() {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING A MEMBERSHIP 2");
    }

    @Override
    public boolean addAccount3() {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING A MEMBERSHIP 3");
        return false;
    }

    @Override
    public void goToSleep() {
        System.out.println(getClass() + ": goToSleep()");
    }
}
