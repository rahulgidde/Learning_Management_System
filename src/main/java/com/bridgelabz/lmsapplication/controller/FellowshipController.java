package com.bridgelabz.lmsapplication.controller;

import com.bridgelabz.lmsapplication.service.IFellowshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fellowship")
public class FellowshipController {

    @Autowired
    IFellowshipService fellowshipService;

    @RequestMapping(value = "/fellowshipcandidatesdata", method = RequestMethod.POST)
    public ResponseEntity<String> getFellowshipCandidateData() {
        fellowshipService.fellowshipCandidatesData();
        return new ResponseEntity<>("Data Copied Successfully", HttpStatus.OK);
    }
}
