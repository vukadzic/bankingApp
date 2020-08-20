package com.logate.banking.repositories;

import com.logate.banking.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    public Optional<User> findByJmbg(String jmbg);
    public User findByUsername(String username);

}
