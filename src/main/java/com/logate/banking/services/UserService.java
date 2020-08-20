package com.logate.banking.services;

import com.logate.banking.domains.Role;
import com.logate.banking.domains.User;
import com.logate.banking.repositories.RoleRepository;
import com.logate.banking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User create (User user, String roleName){

        Set<Role> roles = new HashSet<>();
        List<String> roleNames = new ArrayList<>(Arrays.asList(roleName.split(",")));
        for (String currentRoleName: roleNames) {
            Role roleToAdd = roleRepository.findByName(currentRoleName);
            roles.add(roleToAdd);
        }
        user.setRoles(roles);

        String password = user.getPassword();
        String encodedPassword=passwordEncoder.encode(password);
        user.setPassword(encodedPassword);

        return userRepository.save(user);

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
