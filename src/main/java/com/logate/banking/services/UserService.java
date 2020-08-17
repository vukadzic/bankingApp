package com.logate.banking.services;

import com.logate.banking.domains.Role;
import com.logate.banking.domains.User;
import com.logate.banking.domains.UserHasRoles;
import com.logate.banking.repositories.RoleRepository;
import com.logate.banking.repositories.UserHasRolesRepository;
import com.logate.banking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserHasRolesRepository userHasRolesRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public User create (User user, String roleName){

        String password = user.getPassword();
        String encodedPassword=passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        User savedUser = userRepository.save(user);

        Optional<Role> optRole = roleRepository.findByName(roleName);
        if(optRole.isPresent()) {
            Role role = optRole.get();
            Integer roleId = role.getId();
            Integer userId = savedUser.getId();
            UserHasRoles userHasRoles = new UserHasRoles(userId,roleId);
            userHasRolesRepository.save(userHasRoles);
            Optional<User> optUser2 = userRepository.findById(userId);
            if (optUser2.isPresent()){return optUser2.get();}
        }
        return savedUser;
    }

    public Optional<User> findByJMBG(String jmbg){
        return userRepository.findByJmbg(jmbg);
    }

    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> findAll(){return userRepository.findAll();}
}
