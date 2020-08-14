package com.logate.banking.services;

import com.logate.banking.domains.BankAccount;
import com.logate.banking.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    BankAccountRepository bankAccountRepository;

    public BankAccount create (BankAccount bankAccount){
        return bankAccountRepository.save(bankAccount);
    }

    public BankAccount update (BankAccount bankAccount){
        return bankAccountRepository.save(bankAccount);
    }

    public Optional<BankAccount> findByAccNumber(String accNumber){
        return bankAccountRepository.findByAccountNumber(accNumber);
    }

}
