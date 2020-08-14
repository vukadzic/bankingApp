package com.logate.banking.services;

import com.logate.banking.domains.BankAccount;
import com.logate.banking.domains.User;
import com.logate.banking.dto.BankTransferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BankTransferService {

    @Autowired
    BankAccountService bankAccountService;
    @Autowired
    UserService userService;

    public boolean transfer(BankTransferDTO bankTransferDTO){

        Optional<User> optUser = userService.findById(bankTransferDTO.getUserId());
        Optional<BankAccount> optBankAccFrom = bankAccountService.findByAccNumber(
                                                                                bankTransferDTO.getFromAccountNumber());
        Optional<BankAccount> optBankAccTo= bankAccountService.findByAccNumber(
                                                                                bankTransferDTO.getToAccountNumber());

        if(optUser.isPresent() && optBankAccFrom.isPresent() && optBankAccTo.isPresent()){
            BankAccount bankAccountFrom = optBankAccFrom.get();
            BankAccount bankAccountTo = optBankAccTo.get();

            User fromUser = optUser.get();
            List<BankAccount> bankAccountList = fromUser.getBankAccounts();
            List<String> bankAccNumbers = new ArrayList<>();
            for (BankAccount bankAccount:bankAccountList) {
                bankAccNumbers.add(bankAccount.getAccountNumber());
            }

            if (bankAccNumbers.contains(bankTransferDTO.getFromAccountNumber())
                                        && bankTransferDTO.getUserPassword().equals(fromUser.getPassword())
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
