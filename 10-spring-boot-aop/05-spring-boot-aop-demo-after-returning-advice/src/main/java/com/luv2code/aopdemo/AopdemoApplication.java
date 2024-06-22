package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
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
	public CommandLineRunner commandLineRunner(String[] args, AccountDAO theAccountDAO, MembershipDAO membershipDAO){
		return runner ->{
			System.out.println("Hello World");
			//demoTheBeforeAdvice(theAccountDAO, membershipDAO);
			demoTheAfterReturningAdvice(theAccountDAO);
		};
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
