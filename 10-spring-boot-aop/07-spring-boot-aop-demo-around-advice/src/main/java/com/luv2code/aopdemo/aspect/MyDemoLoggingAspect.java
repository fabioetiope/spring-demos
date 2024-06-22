package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    //AROUND - RE-THROW EXCEPTION
    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundgetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{

        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("---->@Around on the method: " + method);

        //get begin timestamp
        long begin = System.currentTimeMillis();

        //execute the method
        Object result = null;
        try {
            result = theProceedingJoinPoint.proceed();
        }
        catch (Exception exc)  {
            //log the exception
            System.out.println(exc.getMessage());
            //RE-THROW EXCEPTION
            throw exc;
        }

        //get end timestamp
        long end = System.currentTimeMillis();

        //compute duration
        long duration = end - begin;
        System.out.println("DURATION: " + duration/1000.0 + " seconds");
        return result;
    }


    //AROUND - HANDLE EXCEPTION
    /*
    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundgetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{

        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("---->@Around on the method: " + method);

        //get begin timestamp
        long begin = System.currentTimeMillis();

        //execute the method
        Object result = null;
        try {
            result = theProceedingJoinPoint.proceed();
        }
        catch (Exception exc)  {
            //log the exception
            System.out.println(exc.getMessage());
            //give user a custom message
            result = "ACCIDENT ...but go ahead";
        }

        //get end timestamp
        long end = System.currentTimeMillis();

        //compute duration
        long duration = end - begin;
        System.out.println("DURATION: " + duration/1000.0 + " seconds");
        return result;
    }
     */

    //AROUND advice
    /*
    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

        // print out method we are advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // now, let's execute the method
        Object result = theProceedingJoinPoint.proceed();

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        System.out.println("\n=====> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }
     */

    @After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint){
        System.out.println();

        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("@After on the method: " + method);

    }

    @AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc"
    ) //returning value above has to match with the name of the second parameter of the method
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc){
        System.out.println();

        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("@AfterThrowing on the method: " + method);

        System.out.println("The exception is: " + theExc);

    }

    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    ) //returning value above has to match with the name of the second parameter of the method
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result){
        System.out.println();

        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("@AfterReturning on the method: " + method);

        System.out.println("result is: " + result);

        //modifying the result
        converAccountNamesToUpperCase(result);

        System.out.println("new result is: " + result);

    }

    private void converAccountNamesToUpperCase(List<Account> result) {
        for(Account account : result){
            account.setName(account.getName().toUpperCase());
        }
    }

    @Before("com.luv2code.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        System.out.println("---> in @Before advice on add*()");

        //display the method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("method: " + methodSignature);
        //display method arguments
        Object[] args = theJoinPoint.getArgs();
        for (Object tempArg : args) {
            System.out.println(tempArg);
            if (tempArg instanceof  Account) {
                Account theAccount = (Account) tempArg;
                System.out.println("account name: " + theAccount.getName());
                System.out.println("account level: " + theAccount.getLevel());
            }
        }
    }
}
