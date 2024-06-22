package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
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
