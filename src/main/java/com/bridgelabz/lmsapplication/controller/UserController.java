package com.bridgelabz.lmsapplication.controller;

import com.bridgelabz.lmsapplication.dto.UserDto;
import com.bridgelabz.lmsapplication.model.JwtRequest;
import com.bridgelabz.lmsapplication.model.JwtResponse;
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
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        String token = userService.createAuthenticationToken(authenticationRequest);
        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
    }

    //API FOR USER LOGIN
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login() {
        return new ResponseEntity<>("Login Successfully", HttpStatus.OK);
    }

    //API FOR REGISTER USER IN LMS APPLICATION
    @RequestMapping(value = "/registeruser", method = RequestMethod.POST)
    public ResponseEntity<UserDetailModel> register(@RequestBody UserDto user) {
        return new ResponseEntity(userService.loadUserDetails(user), HttpStatus.OK);
    }

    //FORGET PASSWORD API
    @RequestMapping(value = "/sendemail", method = RequestMethod.POST)
    public ResponseEntity<String> sentMail(@RequestParam(value = "emailId") String emailId) throws MessagingException {
        userService.sendEmail(emailId);
        return new ResponseEntity<>("Email Send Successfully", HttpStatus.OK);
    }

    //RESET PASSWORD API
    @RequestMapping(value = "/resetpassword", method = RequestMethod.PUT)
    public ResponseEntity<String> resetPassword(@RequestParam(value = "password") String password, @RequestParam(value = "token") String token) {
        userService.resetPassword(token, password);
        return new ResponseEntity<>("Password Changed Successfully", HttpStatus.OK);
    }
}