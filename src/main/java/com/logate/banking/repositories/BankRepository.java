package com.logate.banking.repositories;

import com.logate.banking.domains.Bank;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BankRepository extends JpaRepository<Bank,Integer> {


}
