package com.bridgelabz.lmsapplication.controller;

import com.bridgelabz.lmsapplication.model.HiredCandidateModel;
import com.bridgelabz.lmsapplication.service.HiredCandidateImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class HiredCandidate {
    @Autowired
    HiredCandidateImpl service;

    @PostMapping("/loadHiredCandidates")
    public String read() throws IOException {
        service.loadHiredCandidateSheet();
        return "Loaded Hired Candidate Successfully";
    }

    @GetMapping("/hiredCandidateList")
    public List getHiredCandidate() {
        return service.getHiredCandidatesList();
    }

    @GetMapping("/hiredCandidateProfile")
    public HiredCandidateModel getCandidateProfile(@RequestParam("candidateName") String candidateName) {
        return service.getHiredCandidatesProfile(candidateName);
    }
}
