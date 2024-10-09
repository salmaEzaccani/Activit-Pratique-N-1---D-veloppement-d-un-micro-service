package org.sid.bank_account_service.entities;

import org.sid.bank_account_service.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

//ca c SPRINGDATARES c pour faire la projection je precise ce que je veux voir comme attribut
@Projection(types = BankAccount.class, name="p1") //cad que lorsque je fais appel jaurais que les attributs que je mets dans cette projection
public interface AccountProjection {
   public String getId();
   public AccountType getType();
   public Double getBalance();
}
