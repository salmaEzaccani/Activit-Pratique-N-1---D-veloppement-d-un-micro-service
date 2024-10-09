package org.sid.bank_account_service;

import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.entities.Customer;
import org.sid.bank_account_service.enums.AccountType;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.sid.bank_account_service.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);
	}

	@Bean  //cad au demrage cet methode va sexecuter
	CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository){ //on fait linjection ici
       return args->{

		   Stream.of("Hassan","Fatna","Aya","Assia").forEach(c->{
			   Customer customer=Customer.builder()
					   .name(c)
					   .build();
			   customerRepository.save(customer);
		   });
		   customerRepository.findAll().forEach(customer -> { //POUR chaque customer je vais creer des comptes
			   for(int i=1;i<10;i++){
				   BankAccount bankAccount=BankAccount.builder()
						   .id(UUID.randomUUID().toString())
						   .type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
						   .balance(10000+Math.random()*90000)
						   .createdAt(new Date())
						   .currency("MAD")
						   .customer(customer)
						   .build();
				   bankAccountRepository.save(bankAccount);
			   }
		   });



	   };
	}
}
