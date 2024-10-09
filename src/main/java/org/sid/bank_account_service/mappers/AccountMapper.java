package org.sid.bank_account_service.mappers;

import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

//JE FAIS MAPPING DUN OBJET VERS UN AUTRE
@Component
public class AccountMapper {
    public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount){
        BankAccountResponseDTO bankAccountResponseDTO=new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAccount,bankAccountResponseDTO);//JE PRENDS DATA DE 1ER OBJET bankAccount ET JE LES METS DANS 2EME
   return bankAccountResponseDTO;
    }
}
