package com.bridgelabz.lmsapplication.controller;

import com.bridgelabz.lmsapplication.configuration.ApplicationConfiguration;
import com.bridgelabz.lmsapplication.dto.EmailDto;
import com.bridgelabz.lmsapplication.dto.ResponseDto;
import com.bridgelabz.lmsapplication.service.IHiredCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hirecandidate")
public class HiredCandidateController {

    @Autowired
    private IHiredCandidateService service;

    //API FOR LOAD HIRED CANDIDATE LIST
    @RequestMapping(value = "/loadhiredcandidates", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> loadHiredCandidates(@RequestParam(value = "filePath") String filePath) {
        return new ResponseEntity<>(new ResponseDto(service.loadHiredCandidateSheet(filePath),
                ApplicationConfiguration.getMessageAccessor().getMessage("109")), HttpStatus.ACCEPTED);
    }

    //API FOR GET HIRED CANDIDATE LIST
    @RequestMapping(value = "/hiredcandidatelist", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto> getHiredCandidateList() {
        return new ResponseEntity<>(new ResponseDto(service.getHiredCandidatesList(),
                ApplicationConfiguration.getMessageAccessor().getMessage("112")), HttpStatus.MULTI_STATUS);
    }

    //API FOR GET HIRED CANDIDATE PROFILE
    @RequestMapping(value = "/hiredcandidateprofile", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto> getCandidateProfile(@RequestParam("candidateId") Long candidateId) {
        return new ResponseEntity<>(new ResponseDto(service.getHiredCandidatesProfile(candidateId),
                ApplicationConfiguration.getMessageAccessor().getMessage("105")), HttpStatus.FOUND);
    }

    //API FOR SEND EMAIL
    @RequestMapping(value = "/sendemail", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> getCandidateStatus(@RequestBody EmailDto emailDto) {
        return new ResponseEntity<>(new ResponseDto(service.sendEmail(emailDto), ApplicationConfiguration
                .getMessageAccessor().getMessage("103")), HttpStatus.OK);
    }

    //API FOR UPDATE STATUS
    @RequestMapping(value = "/updatecandidatestatus", method = RequestMethod.PUT)
    public ResponseEntity<ResponseDto> updateStatus(@RequestParam(value = "id") Long id, @RequestParam(value = "status") String status) {
        return new ResponseEntity<>(new ResponseDto(service.updateStatus(id, status), ApplicationConfiguration
                .getMessageAccessor().getMessage("110")), HttpStatus.ACCEPTED);
    }
}