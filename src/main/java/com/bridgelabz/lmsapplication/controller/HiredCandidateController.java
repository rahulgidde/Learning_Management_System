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
    @RequestMapping(value = "/loadhiredcandidates", method = RequestMethod.POST)
    public String loadHiredCandidates(@RequestParam(value = "filePath") String filePath) throws IOException {
        service.loadHiredCandidateSheet(filePath);
        return "Loaded Hired Candidate Successfully";
    }

    //API FOR GET HIDER CANDIDATE LIST
    @RequestMapping(value = "/hiredcandidatelist", method = RequestMethod.GET)
    public List getHiredCandidateList() {
        return service.getHiredCandidatesList();
    }

    //API FOR GET HIRED CANDIDATE PROFILE
    @RequestMapping(value = "/hiredcandidateprofile", method = RequestMethod.GET)
    public Optional<HiredCandidateModel> getCandidateProfile(@RequestParam("candidateName") Long candidateId) {
        return service.getHiredCandidatesProfile(candidateId);
    }
}
