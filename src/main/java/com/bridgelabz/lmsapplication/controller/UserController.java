package com.bridgelabz.lmsapplication.controller;

import com.bridgelabz.lmsapplication.dto.UserDto;
import com.bridgelabz.lmsapplication.model.JwtRequest;
import com.bridgelabz.lmsapplication.service.IUserService;
import com.bridgelabz.lmsapplication.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //API FOR AUTHENTICATE USER
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        return userService.createAuthenticationToken(authenticationRequest);
    }

    //API FOR USER LOGIN
    @RequestMapping("/login")
    public String login() {
        return "Login Successfully";
    }

    //API FOR REGISTER USER IN LMS APPLICATION
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody UserDto user) {
        return ResponseEntity.ok(userService.loadUserDetails(user));
    }

    //FORGET PASSWORD API
    @RequestMapping(value = "/sendemail", method = RequestMethod.POST)
    public String sentMail(@RequestParam(value = "emailId") String emailId) throws MessagingException {
        userService.sendEmail(emailId);
        return "Email Send Successfully";
    }

    //RESET PASSWORD API
    @RequestMapping(value = "/resetpassword", method = RequestMethod.PUT)
    public String resetPassword(@RequestParam(value = "password") String password, @RequestParam(value = "token") String token) {
        String id = jwtTokenUtil.getUsernameFromToken(token);
        userService.resetPassword(Long.valueOf(id), passwordEncoder.encode(password));
        return "Password Changed Successfully";
    }
}
