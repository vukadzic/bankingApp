package com.logate.banking.services;

import com.logate.banking.domains.User;
import com.logate.banking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User create (User user){
        return userRepository.save(user);
    }

    public Optional<User> findByJMBG(String jmbg){
        return userRepository.findByJmbg(jmbg);
    }

    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }

    public List<User> findAll(){return userRepository.findAll();}
}
