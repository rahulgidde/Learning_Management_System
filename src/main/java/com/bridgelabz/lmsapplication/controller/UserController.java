package com.bridgelabz.lmsapplication.controller;

import com.bridgelabz.lmsapplication.dto.EmailDto;
import com.bridgelabz.lmsapplication.dto.UserDto;
import com.bridgelabz.lmsapplication.model.UserDetail;
import com.bridgelabz.lmsapplication.security.JwtTokenUtil;
import com.bridgelabz.lmsapplication.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@Controller("/user")
public class UserController {

    @Autowired
    JwtUserDetailsService service;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    //API FOR USER LOGIN
    @RequestMapping({"/login"})
    public String login() {
        return "Login Successfully";
    }

    //API FOR REGISTER USER IN LMS APPLICATION
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody UserDto user) {
        return ResponseEntity.ok(service.loadUserDetails(user));
    }

    //FORGET PASSWORD API
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String sentMail(@RequestBody EmailDto emailDto) throws MessagingException {
        UserDetail user = service.findByEmail(emailDto.getEmailId());
        final String token = jwtTokenUtil.generateEmailToken(user.getId());
        service.sendEmail(emailDto, token);
        return "Mail Send Successfully";
    }

    //RESET PASSWORD API
    @RequestMapping(value = "/reset", method = RequestMethod.PUT)
    public String resetPassword(@RequestParam(value = "password") String password, @RequestParam(value = "token") String token) {
        String id = jwtTokenUtil.getUsernameFromToken(token);
        service.resetPassword(Long.valueOf(id), passwordEncoder.encode(password));
        return "Password Changed";
    }
}
