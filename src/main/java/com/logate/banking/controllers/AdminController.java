package com.logate.banking.controllers;

import com.logate.banking.domains.Bank;
import com.logate.banking.domains.BankAccount;
import com.logate.banking.domains.User;
import com.logate.banking.repositories.BankAccountRepository;
import com.logate.banking.services.BankAccountService;
import com.logate.banking.services.BankService;
import com.logate.banking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    @Autowired
    BankService bankService;
    @Autowired
    UserService userService;
    @Autowired
    BankAccountService bankAccountService;

    @PostMapping(value = "/bank")
    public ResponseEntity<Bank> createBank (@RequestBody Bank bank){
        if(bank.getId()!=null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Bank savedBank = bankService.create(bank);
        return new ResponseEntity<>(savedBank,HttpStatus.CREATED);
    }

    @PatchMapping(value = "/bank")
    public ResponseEntity<Bank> updateBank (@RequestBody Bank bank){
        if (bank.getId()==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Bank updatedBank = bankService.update(bank);
        return new ResponseEntity<>(updatedBank,HttpStatus.OK);
    }

    @PutMapping(value = "/bank/deactivate/{id}")
    public ResponseEntity<Void> deactivateBank (@PathVariable(name = "id") Integer id){
        Bank deactivatedBank = bankService.deactivate(id);
        if(deactivatedBank!=null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value="/create-user")
    public ResponseEntity<User> createUser (@Valid @RequestBody User user,
                                            @RequestParam(name = "roleName") String roleName){
        if(user.getId()!=null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User savedUser = userService.create(user,roleName);
        if(savedUser!=null) {
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/create-account")
    public ResponseEntity<BankAccount> createAccount (@RequestBody BankAccount bankAccount,
                                                      @RequestParam(name = "jmbg") String jmbg,
                                                      @RequestParam(name = "bankId") Integer bankId)
    {
        Optional<User> optUser = userService.findByJMBG(jmbg);
        Optional<Bank> optBank = bankService.findById(bankId);
        if(optBank.isPresent() && optUser.isPresent()){
            Bank bank = optBank.get();
            User user = optUser.get();
            bankAccount.setUser(user);
            bankAccount.setBank(bank);
            BankAccount createdBankAccount = bankAccountService.create(bankAccount);
            return new ResponseEntity<>(createdBankAccount,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = new ArrayList<>();
        users = userService.findAll();
        if(users.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
}
