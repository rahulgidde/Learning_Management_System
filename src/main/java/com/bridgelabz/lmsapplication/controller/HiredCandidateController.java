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
    HiredCandidateImpl service;

    @PostMapping("/loadHiredCandidates")
    public String loadHiredCandidates() throws IOException {
        service.loadHiredCandidateSheet();
        return "Loaded Hired Candidate Successfully";
    }

    @GetMapping("/hiredCandidateList")
    public List getHiredCandidateList() {
        return service.getHiredCandidatesList();
    }

    @GetMapping("/hiredCandidateProfile")
    public HiredCandidateModel getCandidateProfile(@RequestParam("candidateName") String candidateName) {
        return service.getHiredCandidatesProfile(candidateName);
    }
}
