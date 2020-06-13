package com.bridgelabz.lmsapplication.controller;

import com.bridgelabz.lmsapplication.configuration.ApplicationConfig;
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

    /**
     * API FOR LOAD HIRED CANDIDATE LIST
     *
     * @param filePath
     * @return response(excel sheet of hired candidates loaded or not)
     */
    @PostMapping(value = "/loadhiredcandidates")
    public ResponseEntity<ResponseDto> loadHiredCandidates(@RequestParam(value = "filePath") String filePath) {
        return new ResponseEntity<>(new ResponseDto(service.loadHiredCandidateSheet(filePath),
                ApplicationConfig.getMessageAccessor().getMessage("109")), HttpStatus.ACCEPTED);
    }

    /**
     * API FOR GET HIRED CANDIDATE LIST
     *
     * @return response(list Of hired candidates)
     */
    @GetMapping(value = "/hiredcandidatelist")
    public ResponseEntity<ResponseDto> getHiredCandidateList() {
        return new ResponseEntity<>(new ResponseDto(service.getHiredCandidatesList(),
                ApplicationConfig.getMessageAccessor().getMessage("112")), HttpStatus.MULTI_STATUS);
    }

    /**
     * API FOR GET HIRED CANDIDATE PROFILE
     *
     * @param candidateId
     * @return response(hired candidate profile)
     */
    @GetMapping(value = "/hiredcandidateprofile")
    public ResponseEntity<ResponseDto> getCandidateProfile(@RequestParam("candidateId") Long candidateId) {
        return new ResponseEntity<>(new ResponseDto(service.getHiredCandidatesProfile(candidateId),
                ApplicationConfig.getMessageAccessor().getMessage("105")), HttpStatus.FOUND);
    }

    /**
     * API FOR SEND EMAIL
     *
     * @param emailId
     * @return response (mail send successfully or not)
     */
    @PostMapping(value = "/sendemail")
    public ResponseEntity<ResponseDto> getCandidateStatus(@RequestParam(value = "emailId") String emailId) {
        return new ResponseEntity<>(new ResponseDto(service.sendEmail(emailId), ApplicationConfig
                .getMessageAccessor().getMessage("103")), HttpStatus.OK);
    }

    /**
     * API FOR UPDATE STATUS
     *
     * @param id
     * @param status
     * @return HiredCandidateModel
     */
    @PutMapping(value = "/updatecandidatestatus")
    public ResponseEntity<ResponseDto> updateStatus(@RequestParam(value = "id") Long id, @RequestParam(value = "status") String status) {
        return new ResponseEntity<>(new ResponseDto(service.updateStatus(id, status), ApplicationConfig
                .getMessageAccessor().getMessage("110")), HttpStatus.ACCEPTED);
    }
}