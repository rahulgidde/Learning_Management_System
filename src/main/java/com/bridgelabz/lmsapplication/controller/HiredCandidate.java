package com.bridgelabz.lmsapplication.controller;

import com.bridgelabz.lmsapplication.service.HiredCandidateImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HiredCandidate {
    @Autowired
    HiredCandidateImpl service;

    @GetMapping("/hiredCandidateList")
    public List getHiredCandidate() {
        return service.getHiredCandidates();
    }
}
