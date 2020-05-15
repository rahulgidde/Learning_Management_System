package com.bridgelabz.lmsapplication.controller;

import com.bridgelabz.lmsapplication.dto.EmailDto;
import com.bridgelabz.lmsapplication.model.UserDetail;
import com.bridgelabz.lmsapplication.security.JwtTokenUtil;
import com.bridgelabz.lmsapplication.service.ForgetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;

@RestController
public class ForgetPasswordController {

    @Autowired
    ForgetPasswordService sender;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    ForgetPasswordService service;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String sentMail(@RequestBody EmailDto emailDto) throws MessagingException {
        UserDetail user = service.findByEmail(emailDto.getEmailId());
        final String token = jwtTokenUtil.generateEmailToken(user.getId());
        sender.sendEmail(emailDto, token);
        return "Mail Send Successfully";
    }

    @RequestMapping(value = "/reset", method = RequestMethod.PUT)
    public String resetPassword(@RequestParam(value = "password") String password, @RequestParam(value = "token") String token) {
        String id = jwtTokenUtil.getUsernameFromToken(token);
        service.resetPassword(Long.valueOf(id), passwordEncoder.encode(password));
        return "Password Changed";
    }
}