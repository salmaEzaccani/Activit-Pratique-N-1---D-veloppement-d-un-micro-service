package org.sid.bank_account_service.web;

import org.sid.bank_account_service.dto.BankAccountRequestDTO;
import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.mappers.AccountMapper;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.sid.bank_account_service.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController //comme c est un web service RESTFUL on va utiliter RestController
@RequestMapping("/api") //cad que pour acceder au autre url je dois debuter vec /api/bankAccount...
public class AccountRestController {
  // @Autowired c pour faire injection des dependance ou bien faire injection via constructeur
     private BankAccountRepository bankAccountRepository;//injecter la couche Repo car on a besoin dacceder a BDD
     private AccountService accountService;
     private AccountMapper accountMapper;


    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    //ALORS ON A CREE UNE REST API

    @GetMapping("/bankAccounts") //on ajoute cet RESTAPI POUR consulter la liste des comptes
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();

    }

    @GetMapping("/bankAccounts/{id}") //consulter un compte
    public BankAccount bankAccount(@PathVariable String id){ //cad c un var qui va se reccuperer a partir de path
        return bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }

    @PostMapping("/bankAccounts")//pour ajouter on utilise POST
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO){ //cad les donner de banqueaccount va le reccupere de le corps de rqt en format json
       // JELIMINE CA CAR IL YA DANS dto if(bankAccount.getId()==null)  bankAccount.setId(UUID.randomUUID().toString());
       return accountService.addAccount(requestDTO);
    }

    @PutMapping("/bankAccounts/   {id}")//pour maj on utilise PUT
    public BankAccount update(@PathVariable String id,@RequestBody BankAccount bankAccount){ //cad les donner de banqueaccount va le reccupere de le corps de rqt en format json
        BankAccount account=bankAccountRepository.findById(id).orElseThrow();
      if(bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
      if(bankAccount.getCreatedAt()!=null)  account.setCreatedAt(new Date());
      if(bankAccount.getType()!=null)  account.setType(bankAccount.getType());
      if(bankAccount.getCurrency()!=null)  account.setCurrency(bankAccount.getCurrency());
      return bankAccountRepository.save(account);
    }

    @DeleteMapping("/bankAccounts/{id}") //consulter un compte
    public void deleteAccount(@PathVariable String id){ //cad c un var qui va se reccuperer a partir de path
        bankAccountRepository.deleteById(id);
    }
}
