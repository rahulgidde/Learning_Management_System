package com.bridgelabz.lmsapplication.controller;

import com.bridgelabz.lmsapplication.configuration.ApplicationConfiguration;
import com.bridgelabz.lmsapplication.dto.BankDetailsDto;
import com.bridgelabz.lmsapplication.dto.PersonalInfoDto;
import com.bridgelabz.lmsapplication.dto.QualificationDto;
import com.bridgelabz.lmsapplication.dto.ResponseDto;
import com.bridgelabz.lmsapplication.model.FellowshipModel;
import com.bridgelabz.lmsapplication.service.IFellowshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fellowship")
public class FellowshipController {

    @Autowired
    private IFellowshipService fellowshipService;

    //API FOR COPY ONE TABLE DATA TO ANOTHER TABLE
    @PostMapping(value = "/fellowshipcandidatesdata")
    public ResponseEntity<ResponseDto> fellowshipCandidatesData() {
        return new ResponseEntity<>(new ResponseDto(fellowshipService.fellowshipCandidatesData(),
                ApplicationConfiguration.getMessageAccessor().getMessage("109")), HttpStatus.ACCEPTED);
    }

    //API FOR GET CANDIDATE COUNT
    @GetMapping(value = "/fellowshipcandidatecount")
    public ResponseEntity<ResponseDto> fellowshipCandidateCount() {
        return new ResponseEntity<>(new ResponseDto(fellowshipService.fellowshipCandidateCount(),
                ApplicationConfiguration.getMessageAccessor().getMessage("113")), HttpStatus.FOUND);
    }

    //API FOR SEND JOB OFFER MAIL
    @PostMapping(value = "/sendjoboffer")
    public ResponseEntity<ResponseDto> sendJobOfferMail() {
        return new ResponseEntity<>(new ResponseDto(fellowshipService.jobOfferMail(),
                ApplicationConfiguration.getMessageAccessor().getMessage("103")), HttpStatus.OK);
    }

    //API FOR UPDATE CANDIDATE PERSONAL INFORMATION
    @PutMapping(value = "/updatepersonalnfo")
    public ResponseEntity<ResponseDto> updateCandidatePersonalInfo(@RequestParam(value = "id") Long id,
                                                                   @RequestBody PersonalInfoDto personalInfoDto) {
        FellowshipModel fellowshipModel = fellowshipService.personalInfo(id, personalInfoDto);
        return new ResponseEntity<>(new ResponseDto(fellowshipModel, ApplicationConfiguration.getMessageAccessor()
                .getMessage("114")), HttpStatus.FOUND);
    }

    //API FOR UPDATE BANK INFORMATION
    @PostMapping(value = "/updatebankinfo")
    public ResponseEntity<ResponseDto> updateBankDetails(@RequestBody BankDetailsDto bankDetailsDto) {
        return new ResponseEntity<>(new ResponseDto(fellowshipService.bankDetails(bankDetailsDto),
                ApplicationConfiguration.getMessageAccessor().getMessage("108")), HttpStatus.ACCEPTED);
    }

    //API FOR UPDATE EDUCATIONAL DETAILS
    @PostMapping(value = "/updateeducationalinfo")
    public ResponseEntity<ResponseDto> updateEducationalInfo(@RequestBody QualificationDto qualificationDto) {
        return new ResponseEntity<>(new ResponseDto(fellowshipService.educationalInfo(qualificationDto),
                ApplicationConfiguration.getMessageAccessor().getMessage("111")), HttpStatus.ACCEPTED);
    }

    //API FOR UPLOAD DOCUMENTS
    @PostMapping("/uploaddocument")
    public ResponseEntity<ResponseDto> uploadFile(@RequestParam("file") MultipartFile file,
                                                  @RequestParam(value = "documentDto") String documentDto) {
        String url = fellowshipService.uploadFile(file, documentDto);
        return new ResponseEntity<>(new ResponseDto(url, ApplicationConfiguration.getMessageAccessor()
                .getMessage("116")), HttpStatus.ACCEPTED);
    }
}