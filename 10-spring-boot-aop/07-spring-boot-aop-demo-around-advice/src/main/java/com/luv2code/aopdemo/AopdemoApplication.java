package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import com.luv2code.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(String[] args, AccountDAO theAccountDAO, MembershipDAO membershipDAO, TrafficFortuneService theTrafficFortuneService){
		return runner ->{
			System.out.println("Hello World");
			//demoTheBeforeAdvice(theAccountDAO, membershipDAO);
			//demoTheAfterReturningAdvice(theAccountDAO);
			//demoTheAfterThrowingAdvice(theAccountDAO);
			//demoTheAfter(theAccountDAO);
			//demoTheAround(theTrafficFortuneService);
			//demoTheAroundHandleException(theTrafficFortuneService);
			demoTheAroundRethrowException(theTrafficFortuneService);
		};
	}

	private void demoTheAroundRethrowException(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("Main program: demoTheAroundRethrowException");
		System.out.println("calling getFortune()");
		boolean tripWire = true;
		String data = theTrafficFortuneService.getFortune(tripWire);
		System.out.println("My fortune is: " + data);
		System.out.println("DONE");
	}

	private void demoTheAroundHandleException(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("Main program: demoTheAroundHandleException");
		System.out.println("calling getFortune()");
		boolean tripWire = true;
		String data = theTrafficFortuneService.getFortune(tripWire);
		System.out.println("My fortune is: " + data);
		System.out.println("DONE");
	}

	private void demoTheAround(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("Main program: demoTheAround");
		System.out.println("calling getFortune()");
		String data = theTrafficFortuneService.getFortune();
		System.out.println("My fortune is: " + data);
		System.out.println("DONE");
	}

	private void demoTheAfter(AccountDAO theAccountDAO) {
		List<Account> accounts = null;

		try {
			boolean tripWire = false;
			accounts = theAccountDAO.findAccounts(tripWire);
		}
		catch (Exception exc){
			System.out.println();
			System.out.println("Main Program: caught exception " +exc);
		}

		System.out.println("demoTheAfterThrowingAdvice -> " + accounts);
	}

	private void demoTheAfterThrowingAdvice(AccountDAO theAccountDAO) {
		List<Account> accounts = null;

		try {
			boolean tripWire = true;
			accounts = theAccountDAO.findAccounts(tripWire);
		}
		catch (Exception exc){
			System.out.println();
			System.out.println("Main Program: caught exception " +exc);
		}

		System.out.println("demoTheAfterThrowingAdvice -> " + accounts);
	}

	private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {
		List<Account> accounts = theAccountDAO.findAccounts();
		System.out.println("demoTheAfterReturningAdvice -> " + accounts);
	}

	private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO membershipDAO) {
		//theAccountDAO.addAccount();

		Account account = new Account();
		account.setName("Mad");
		account.setLevel("2");
		//theAccountDAO.addAccount(account);
		theAccountDAO.addAccount(account, true);

		membershipDAO.addAccount();

		membershipDAO.addAccount2();

		membershipDAO.addAccount3();

		theAccountDAO.doWork();
		membershipDAO.goToSleep();

		System.out.println();
		String name = theAccountDAO.getName();
		String code = theAccountDAO.getServiceCode();
		theAccountDAO.setServiceCode("a");
		theAccountDAO.setName("b");

	}
}
