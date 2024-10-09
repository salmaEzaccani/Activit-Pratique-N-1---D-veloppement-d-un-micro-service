package org.sid.bank_account_service.repositories;

import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
  @RestResource(path="/byType") //pour changer le nom de la methode
  List<BankAccount> findByType(@Param("t") AccountType type); //ca c on utilisant spring data rest jutilise cette methode directement sans faire appel **sinon si jutilise restController ja dois mettre dedans une methode qu appel cette methose
}
