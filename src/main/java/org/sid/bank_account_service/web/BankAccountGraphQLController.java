package org.sid.bank_account_service.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.bank_account_service.dto.BankAccountRequestDTO;
import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.entities.Customer;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.sid.bank_account_service.repositories.CustomerRepository;
import org.sid.bank_account_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQLController {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerRepository customerRepository;


    @QueryMapping    //annotation graphql
    public List<BankAccount> accountsList(){
        return bankAccountRepository.findAll();
    }

    @QueryMapping    //annotation graphql
    public BankAccount bankAccountById(@Argument String id){ //POUR UTILISER CETTE METHODE IL FAUT LA DECLARE DANS SHCEMA
        return bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }

    //DANS REST LA METHODE SAVE FAIT LA MEME CHOSE
    @MutationMapping    //toute les operation de modif c mutation
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccount){ //BankAccount C UN INPUT
     return  accountService.addAccount(bankAccount);
    }

    @MutationMapping    //toute les operation de modif c mutation
    public BankAccountResponseDTO updateAccount(@Argument String id,@Argument BankAccountRequestDTO bankAccount){ //BankAccount C UN INPUT
        return  accountService.updateAccount(id,bankAccount);
    }

    @MutationMapping    //toute les operation de modif c mutation
    public void deleteAccount(@Argument String id){ //BankAccount C UN INPUT
        bankAccountRepository.deleteById(id);

    }

    @QueryMapping
    public List<Customer> customers(){
        return customerRepository.findAll();
    }

}

// 1ERE METHODE
/*@Data @NoArgsConstructor @AllArgsConstructor
class BankAccountDTO {
    private String type;
    private Double balance;
    private String currency;
}

//2EME METHODE
record BankAccountDTO(Double balance, String type,String currency){
}*/

