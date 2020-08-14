package com.logate.banking.services;

import com.logate.banking.domains.Bank;
import com.logate.banking.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankService {

    @Autowired
    BankRepository bankRepository;

    public Bank create (Bank bank){
        return bankRepository.save(bank);
    }

    public Bank update (Bank bank){
        Optional<Bank> optBank = bankRepository.findById(bank.getId());
        if(optBank.isPresent()) {
            Bank dbBank = optBank.get();
            if (bank.getName() != null) {
                dbBank.setName(bank.getName());
            }
            if (bank.getActive() != null) {
                dbBank.setActive(bank.getActive());
            }
            if (bank.getAddress() != null) {
                dbBank.setAddress(bank.getAddress());
            }
            return bankRepository.save(dbBank);
        }
        return null;
    }


    public Bank deactivate(Integer id){
        Optional<Bank> optBank = bankRepository.findById(id);
        if (optBank.isPresent()){
            Bank bank = optBank.get();
            bank.setActive(false);
            Bank savedBank = update(bank);
            return savedBank;
        }
        return null;
    }

    public Optional<Bank> findById(Integer id){
        return bankRepository.findById(id);
    }



}
