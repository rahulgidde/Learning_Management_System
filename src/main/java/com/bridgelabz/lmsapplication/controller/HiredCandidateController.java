package com.bridgelabz.lmsapplication.controller;

import com.bridgelabz.lmsapplication.model.HiredCandidateModel;
import com.bridgelabz.lmsapplication.service.HiredCandidateImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/hireCandidate")
public class HiredCandidateController {
    @Autowired
    private HiredCandidateImpl service;

    //API FOR LOAD HIDER CANDIDATE LIST
    @PostMapping("/loadHiredCandidates")
    public String loadHiredCandidates() throws IOException {
        service.loadHiredCandidateSheet();
        return "Loaded Hired Candidate Successfully";
    }

    //API FOR GET HIDER CANDIDATE LIST
    @GetMapping("/hiredCandidateList")
    public List getHiredCandidateList() {
        return service.getHiredCandidatesList();
    }

    //API FOR GET HIRED CANDIDATE PROFILE
    @GetMapping("/hiredCandidateProfile")
    public HiredCandidateModel getCandidateProfile(@RequestParam("candidateName") String candidateName) {
        return service.getHiredCandidatesProfile(candidateName);
    }
}
