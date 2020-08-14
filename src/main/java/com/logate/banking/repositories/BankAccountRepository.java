package com.logate.banking.repositories;

import com.logate.banking.domains.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount,Integer> {

    Optional<BankAccount> findByAccountNumber(String accNum);
}
