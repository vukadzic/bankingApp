package com.logate.banking.controllers;

import com.logate.banking.dto.BankTransferDTO;
import com.logate.banking.services.BankTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/user",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    BankTransferService bankTransferService;

    @PutMapping(value = "/transfer")
    public ResponseEntity<Void> transfer (@RequestBody BankTransferDTO bankTransferDTO){
        boolean response = bankTransferService.transfer(bankTransferDTO);
        if (response) {return new ResponseEntity<>(HttpStatus.OK);}
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
