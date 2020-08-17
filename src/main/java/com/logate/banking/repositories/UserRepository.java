package com.logate.banking.repositories;

import com.logate.banking.domains.Bank;
import com.logate.banking.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    public Optional<User> findByJmbg(String jmbg);
    public Optional<User> findById(Integer Id);
    public User findByUsername(String username);

}
