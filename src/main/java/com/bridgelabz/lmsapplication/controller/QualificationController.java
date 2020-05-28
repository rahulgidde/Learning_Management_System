package com.bridgelabz.lmsapplication.controller;

import com.bridgelabz.lmsapplication.dto.QualificationDto;
import com.bridgelabz.lmsapplication.service.IEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qualification")
public class QualificationController {

    @Autowired
    IEducationService educationService;

    //API FOR UPDATE EDUCATIONAL DETAILS
    @RequestMapping(value="/updateeducationalinfo",method = RequestMethod.POST)
    public ResponseEntity<String> updateEducationalInfo(@RequestBody QualificationDto qualificationDto){
        educationService.educationalInfo(qualificationDto);
        return new ResponseEntity<>("Educational Information Updated Successfully", HttpStatus.OK);
    }
}
