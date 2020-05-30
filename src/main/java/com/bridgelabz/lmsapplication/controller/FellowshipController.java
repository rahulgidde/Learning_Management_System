package com.bridgelabz.lmsapplication.controller;

import com.bridgelabz.lmsapplication.configuration.ApplicationConfiguration;
import com.bridgelabz.lmsapplication.dto.PersonalInfoDto;
import com.bridgelabz.lmsapplication.dto.ResponseDto;
import com.bridgelabz.lmsapplication.model.FellowshipModel;
import com.bridgelabz.lmsapplication.service.IFellowshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fellowship")
public class FellowshipController {

    @Autowired
    private IFellowshipService fellowshipService;

    //API FOR COPY ONE TABLE DATA TO ANOTHER TABLE
    @RequestMapping(value = "/fellowshipcandidatesdata", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> fellowshipCandidatesData() {
        return new ResponseEntity<>(new ResponseDto(fellowshipService.fellowshipCandidatesData(),
                ApplicationConfiguration.getMessageAccessor().getMessage("109")), HttpStatus.ACCEPTED);
    }

    //API FOR GET CANDIDATE COUNT
    @RequestMapping(value = "/fellowshipcandidatecount", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto> fellowshipCandidateCount() {
        return new ResponseEntity<>(new ResponseDto(fellowshipService.fellowshipCandidateCount(),
                ApplicationConfiguration.getMessageAccessor().getMessage("113")), HttpStatus.FOUND);
    }

    //API FOR SEND JOB OFFER MAIL
    @RequestMapping(value = "/sendjoboffer", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> sendJobOfferMail() {
        return new ResponseEntity<>(new ResponseDto(fellowshipService.jobOfferMail(),
                ApplicationConfiguration.getMessageAccessor().getMessage("103")), HttpStatus.OK);
    }

    @RequestMapping(value = "/updatepersonalnfo", method = RequestMethod.PUT)
    public ResponseEntity<ResponseDto> updateCandidatePersonalInfo(@RequestParam(value = "id") Long id,
                                                                   @RequestBody PersonalInfoDto personalInfoDto) {
        FellowshipModel fellowshipModel = fellowshipService.personalInfo(id, personalInfoDto);
        return new ResponseEntity<>(new ResponseDto(fellowshipModel, ApplicationConfiguration.getMessageAccessor()
                .getMessage("114")), HttpStatus.FOUND);
    }
}
