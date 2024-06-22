package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect //optional if there are only PointCut declarations and no aspects
public class AopExpressions {

    //Declaring a pointcut
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    public void forDaoPackage(){}

    //Declaring pointcut for getter methods
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
    public void forDaoPackageGetter(){}

    //Declaring pointcut for setter methods
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
    public void forDaoPackageSetter(){}

    //Declaring pointcut for dao package, excluding getter and  setter methods
    @Pointcut("forDaoPackage() && !(forDaoPackageGetter() || forDaoPackageSetter())")
    public void forDaoPackageNoGetterSetter(){}
}
