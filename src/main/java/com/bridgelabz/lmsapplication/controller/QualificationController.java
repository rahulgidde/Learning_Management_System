package com.bridgelabz.lmsapplication.controller;

import com.bridgelabz.lmsapplication.dto.EducationalInfoDto;
import com.bridgelabz.lmsapplication.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/education")
public class EducationController {

    @Autowired
    EducationService educationService;

    //API FOR UPDATE EDUCATIONAL DETAILS
    @RequestMapping(value="/updateeducationalinfo",method = RequestMethod.POST)
    public ResponseEntity<String> updateEducationalInfo(@RequestBody EducationalInfoDto educationalInfoDto){
        educationService.educationalInfo(educationalInfoDto);
        return new ResponseEntity<>("Educational Information Updated", HttpStatus.OK);
    }
}
