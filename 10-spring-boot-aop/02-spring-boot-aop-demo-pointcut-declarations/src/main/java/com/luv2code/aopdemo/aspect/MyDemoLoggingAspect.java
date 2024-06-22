package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    //match any method public void addAccount()
    /* match on any method public void addAccount()
    @Before("execution(public void addAccount())")
    public void beforeAddAccountAdvice(){
        System.out.println("---> in @Before advice on addAccount()");
    }
    */

    //match only for the method addAccount() of the class: com.luv2code.aopdemo.dao.AccountDAO
    /*
    @Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
    public void beforeAddAccountAdvice(){
        System.out.println("---> in @Before advice on addAccount()");
    }
    */


    //match any method public void that starts with "add"
    /*
    @Before("execution(public void add*())")
    public void beforeAddAccountAdvice(){
        System.out.println("---> in @Before advice on add*()");
    }
     */

    //match any method with any modifier, that returns void and that starts with "add"
    /*
    @Before("execution(void add*())")
    public void beforeAddAccountAdvice() {
        System.out.println("---> in @Before advice on add*()");
    }
     */

    //match any method with any modifier, that returns any object and that starts with "add"
    /*
    @Before("execution(* add*())")
    public void beforeAddAccountAdvice() {
        System.out.println("---> in @Before advice on add*()");
    }
    */

    //match any method with any modifier, that returns any object,
    // that starts with "add" and that has as parameter Account (need to use fully qualified classname
    /*
    @Before("execution(* add*(com.luv2code.aopdemo.Account))")
    public void beforeAddAccountAdvice() {
        System.out.println("---> in @Before advice on add*()");
    }
     */

    //match any method with any modifier, that returns any object,
    // that starts with "add", that has as parameter Account (need to use fully qualified classname
    //followed by any number of parameters (use "..")
    /*
    @Before("execution(* add*(com.luv2code.aopdemo.Account, ..))")
    public void beforeAddAccountAdvice() {
        System.out.println("---> in @Before advice on add*()");
    }
     */

    //match any method with any modifier, that returns any object,
    // that starts with "add" and that has any number of parameters
    /*
    @Before("execution(* add*(..))")
    public void beforeAddAccountAdvice() {
        System.out.println("---> in @Before advice on add*()");
    }
     */

    //match any method of any class only of the package com.luv2code.aopdemo.dao
    // with any modifier, that returns any object,
    // that starts with "add" and that has any number of parameters
    /*
    @Before("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    public void beforeAddAccountAdvice() {
        System.out.println("---> in @Before advice on add*()");
    }
     */

    //Declaring a pointcut
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    private void forDaoPackage(){}

    //Using pointcut declaration
    /*
    @Before("forDaoPackage()")
    public void beforeAddAccountAdvice() {
        System.out.println("---> in @Before advice on add*()");
    }
     */

    //Reusing pointcut declaration
    /*
    @Before("forDaoPackage()")
    public void performApiAnalytics() {
        System.out.println("Performing API Analytics");
    }
     */

    //Declaring pointcut for getter methods
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
    private void forDaoPackageGetter(){}

    //Declaring pointcut for setter methods
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
    private void forDaoPackageSetter(){}

    //Declaring pointcut for dao package, excluding getter and  setter methods
    @Pointcut("forDaoPackage() && !(forDaoPackageGetter() || forDaoPackageSetter())")
    private void forDaoPackageNoGetterSetter(){}

    /////////////////////////////////////////////////////////////////////////////////////
    @Before("forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice() {
        System.out.println("---> in @Before advice on add*()");
    }

    @Before("forDaoPackageNoGetterSetter()")
    public void performApiAnalytics() {
        System.out.println("---> Performing API Analytics");
    }

    @Before("forDaoPackageNoGetterSetter()")
    public void logToCloud() {
        System.out.println("---> Logging to Cloud");
    }

}
