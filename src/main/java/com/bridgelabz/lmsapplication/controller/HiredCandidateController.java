package com.bridgelabz.lmsapplication.controller;

import com.bridgelabz.lmsapplication.model.HiredCandidateModel;
import com.bridgelabz.lmsapplication.service.IHiredCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hirecandidate")
public class HiredCandidateController {

    @Autowired
    private IHiredCandidateService service;

    //API FOR LOAD HIRED CANDIDATE LIST
    @RequestMapping(value = "/loadhiredcandidates", method = RequestMethod.POST)
    public ResponseEntity<String> loadHiredCandidates(@RequestParam(value = "filePath") String filePath) throws IOException {
        service.loadHiredCandidateSheet(filePath);
        return new ResponseEntity<>("Loaded Hired Candidate Successfully",HttpStatus.OK);
    }

    //API FOR GET HIRED CANDIDATE LIST
    @RequestMapping(value = "/hiredcandidatelist", method = RequestMethod.GET)
    public ResponseEntity<List> getHiredCandidateList() {
        return new ResponseEntity<>(service.getHiredCandidatesList(),HttpStatus.OK);
    }

    //API FOR GET HIRED CANDIDATE PROFILE
    @RequestMapping(value = "/hiredcandidateprofile", method = RequestMethod.GET)
    public ResponseEntity<Optional<HiredCandidateModel>> getCandidateProfile(@RequestParam("candidateId") Long candidateId) {
        return new ResponseEntity<>(service.getHiredCandidatesProfile(candidateId),HttpStatus.FOUND);
    }
}
