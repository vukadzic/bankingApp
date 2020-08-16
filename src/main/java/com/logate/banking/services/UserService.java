package com.logate.banking.services;

import com.logate.banking.domains.Role;
import com.logate.banking.domains.User;
import com.logate.banking.repositories.RoleRepository;
import com.logate.banking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public User create (User user, String roleName){
        Optional<Role> optRole =  roleRepository.findByName(roleName);
        if(optRole.isPresent()) {
            Role role = optRole.get();
            user.setRole(role);
            return userRepository.save(user);
        }
        return null;

    }

    public Optional<User> findByJMBG(String jmbg){
        return userRepository.findByJmbg(jmbg);
    }

    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }

    public List<User> findAll(){return userRepository.findAll();}
}
