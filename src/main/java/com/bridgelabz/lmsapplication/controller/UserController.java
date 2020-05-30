package com.bridgelabz.lmsapplication.controller;

import com.bridgelabz.lmsapplication.configuration.ApplicationConfiguration;
import com.bridgelabz.lmsapplication.dto.ResponseDto;
import com.bridgelabz.lmsapplication.dto.UserDto;
import com.bridgelabz.lmsapplication.model.JwtRequest;
import com.bridgelabz.lmsapplication.model.UserDetailModel;
import com.bridgelabz.lmsapplication.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    //API FOR AUTHENTICATE USER
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        String token = userService.createAuthenticationToken(authenticationRequest);
        return new ResponseEntity<>(new ResponseDto(token, ApplicationConfiguration.getMessageAccessor()
                .getMessage("106")), HttpStatus.OK);
    }

    //API FOR USER LOGIN
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login() {
        return new ResponseEntity<>(ApplicationConfiguration.getMessageAccessor().getMessage("102"), HttpStatus.OK);
    }

    //API FOR REGISTER USER IN LMS APPLICATION
    @RequestMapping(value = "/registeruser", method = RequestMethod.POST)
    public ResponseEntity<UserDetailModel> register(@RequestBody UserDto user) {
        return new ResponseEntity(new ResponseDto(userService.loadUserDetails(user), ApplicationConfiguration
                .getMessageAccessor().getMessage("101")), HttpStatus.ACCEPTED);
    }

    //FORGET PASSWORD API
    @RequestMapping(value = "/sendemail", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> sentMail(@RequestParam(value = "emailId") String emailId) throws MessagingException {
        return new ResponseEntity<>(new ResponseDto(userService.sendEmail(emailId), ApplicationConfiguration.getMessageAccessor().
                getMessage("103")), HttpStatus.OK);
    }

    //RESET PASSWORD API
    @RequestMapping(value = "/resetpassword", method = RequestMethod.PUT)
    public ResponseEntity<ResponseDto> resetPassword(@RequestParam(value = "password") String password,
                                                     @RequestParam(value = "token") String token) {
        return new ResponseEntity<>(new ResponseDto(userService.resetPassword(token, password), ApplicationConfiguration
                .getMessageAccessor().getMessage("104")), HttpStatus.OK);
    }
}