package com.logate.banking.repositories;

import com.logate.banking.domains.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank,Integer> {

    @Override
    Optional<Bank> findById(Integer id);
}
