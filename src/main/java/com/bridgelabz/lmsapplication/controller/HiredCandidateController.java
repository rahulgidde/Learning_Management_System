package com.bridgelabz.lmsapplication.controller;

import com.bridgelabz.lmsapplication.dto.EmailDto;
import com.bridgelabz.lmsapplication.model.HiredCandidateModel;
import com.bridgelabz.lmsapplication.service.IHiredCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hirecandidate")
public class HiredCandidateController {

    @Autowired
    private IHiredCandidateService service;

    //API FOR LOAD HIRED CANDIDATE LIST
    @RequestMapping(value = "/loadhiredcandidates", method = RequestMethod.POST)
    public ResponseEntity<String> loadHiredCandidates(@RequestParam(value = "filePath") String filePath) {
        service.loadHiredCandidateSheet(filePath);
        return new ResponseEntity<>("Loaded Hired Candidate Successfully", HttpStatus.ACCEPTED);
    }

    //API FOR GET HIRED CANDIDATE LIST
    @RequestMapping(value = "/hiredcandidatelist", method = RequestMethod.GET)
    public ResponseEntity<List> getHiredCandidateList() {
        return new ResponseEntity<>(service.getHiredCandidatesList(), HttpStatus.FOUND);
    }

    //API FOR GET HIRED CANDIDATE PROFILE
    @RequestMapping(value = "/hiredcandidateprofile", method = RequestMethod.GET)
    public ResponseEntity<HiredCandidateModel> getCandidateProfile(@RequestParam("candidateId") Long candidateId) {
        return new ResponseEntity<>(service.getHiredCandidatesProfile(candidateId), HttpStatus.FOUND);
    }

    //API FOR SEND EMAIL
    @RequestMapping(value = "/sendemail", method = RequestMethod.POST)
    public ResponseEntity<String> getCandidateStatus(@RequestBody EmailDto emailDto) {
        service.sendEmail(emailDto);
        return new ResponseEntity<>("Email Send Successfully", HttpStatus.OK);
    }

    //API FOR UPDATE STATUS
    @RequestMapping(value = "/updatecandidatestatus", method = RequestMethod.PUT)
    public ResponseEntity<HiredCandidateModel> updateStatus(@RequestParam(value = "id") Long id, @RequestParam(value = "status") String status) {
        return new ResponseEntity<>(service.updateStatus(id, status), HttpStatus.ACCEPTED);
    }
}