package com.bridgelabz.lmsapplication.controller;

import com.bridgelabz.lmsapplication.model.HiredCandidateModel;
import com.bridgelabz.lmsapplication.service.IHiredCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hirecandidate")
public class HiredCandidateController {

    @Autowired
    private IHiredCandidateService service;

    //API FOR LOAD HIDER CANDIDATE LIST
    @PostMapping("/loadhiredcandidates")
    public String loadHiredCandidates() throws IOException {
        service.loadHiredCandidateSheet();
        return "Loaded Hired Candidate Successfully";
    }

    //API FOR GET HIDER CANDIDATE LIST
    @GetMapping("/hiredcandidatelist")
    public List getHiredCandidateList() {
        return service.getHiredCandidatesList();
    }

    //API FOR GET HIRED CANDIDATE PROFILE
    @GetMapping("/hiredcandidateprofile")
    public Optional<HiredCandidateModel> getCandidateProfile(@RequestParam("candidateName") Long candidateId) {
        return service.getHiredCandidatesProfile(candidateId);
    }
}
