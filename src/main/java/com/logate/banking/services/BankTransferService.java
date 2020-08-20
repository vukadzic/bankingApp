package com.logate.banking.services;

import com.logate.banking.domains.BankAccount;
import com.logate.banking.domains.User;
import com.logate.banking.dto.BankTransferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BankTransferService {

    @Autowired
    private BankAccountService bankAccountService;
    @Autowired
    private UserService userService;

    public boolean transfer(BankTransferDTO bankTransferDTO){

        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        User fromUser = userService.findByUsername(username);
        Optional<BankAccount> optBankAccFrom = bankAccountService.findByAccNumber(
                                                                                bankTransferDTO.getFromAccountNumber());
        Optional<BankAccount> optBankAccTo= bankAccountService.findByAccNumber(
                                                                                bankTransferDTO.getToAccountNumber());

        if(optBankAccFrom.isPresent() && optBankAccTo.isPresent()){
            BankAccount bankAccountFrom = optBankAccFrom.get();
            BankAccount bankAccountTo = optBankAccTo.get();

            List<BankAccount> bankAccountList = fromUser.getBankAccounts();
            List<String> bankAccNumbers = new ArrayList<>();
            for (BankAccount bankAccount:bankAccountList) {
                bankAccNumbers.add(bankAccount.getAccountNumber());
            }

            if (bankAccNumbers.contains(bankTransferDTO.getFromAccountNumber())
                                        && bankAccountFrom.getCurrentBalance()>=bankTransferDTO.getAmount()
                                        && bankTransferDTO.getAmount()>=0){
                bankAccountFrom.setCurrentBalance(bankAccountFrom.getCurrentBalance()- bankTransferDTO.getAmount());
                bankAccountTo.setCurrentBalance(bankAccountTo.getCurrentBalance() + bankTransferDTO.getAmount());
                bankAccountService.update(bankAccountFrom);
                bankAccountService.update(bankAccountTo);
                return true;
            }
        }
        return false;

    }


}
