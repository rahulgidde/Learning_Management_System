package com.bridgelabz.lmsapplication.controller;

import com.bridgelabz.lmsapplication.dto.PersonalInfoDto;
import com.bridgelabz.lmsapplication.service.IFellowshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fellowship")
public class FellowshipController {

    @Autowired
    IFellowshipService fellowshipService;

    //API FOR COPY ONE TABLE DATA TO ANOTHER TABLE
    @RequestMapping(value = "/fellowshipcandidatesdata", method = RequestMethod.POST)
    public ResponseEntity<String> getFellowshipCandidateData() {
        fellowshipService.fellowshipCandidatesData();
        return new ResponseEntity<>("Data Copied Successfully", HttpStatus.OK);
    }

    //API FOR GET CANDIDATE COUNT
    @RequestMapping(value = "/fellowshipcandidatecount", method = RequestMethod.GET)
    public ResponseEntity<Integer> getFellowshipCandidateCount() {
        return new ResponseEntity<>(fellowshipService.FellowshipCandidateCount(), HttpStatus.OK);
    }

    //API FOR SEND JOB OFFER MAIL
    @RequestMapping(value = "/sendjoboffer", method = RequestMethod.POST)
    public ResponseEntity<String> getJobOfferMail() {
        fellowshipService.jobOfferMail();
        return new ResponseEntity<>("Mail Send Successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/updatepersonalnfo", method = RequestMethod.PUT)
    public ResponseEntity<String> updatePersonalInfo(@RequestParam(value = "id") Long id, @RequestBody PersonalInfoDto personalInfoDto) {
        fellowshipService.personalInfo(id, personalInfoDto);
        return new ResponseEntity<>("Personal Information Updated", HttpStatus.OK);
    }
}
