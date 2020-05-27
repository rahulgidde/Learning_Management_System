package com.bridgelabz.lmsapplication.controller;

import com.bridgelabz.lmsapplication.dto.BankDetailsDto;
import com.bridgelabz.lmsapplication.service.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankController {
    @Autowired
    IBankService bankService;

    //API FOR UPDATE BANK INFORMATION
    @RequestMapping(value = "/updatebankinfo", method = RequestMethod.POST)
    public ResponseEntity<String> getBankDetails(@RequestBody BankDetailsDto bankDetailsDto) {
        bankService.bankDetails(bankDetailsDto);
        return new ResponseEntity<>("Bank Details Updated", HttpStatus.OK);
    }
}
