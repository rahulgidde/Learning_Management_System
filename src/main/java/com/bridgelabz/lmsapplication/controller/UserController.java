package com.bridgelabz.lmsapplication.controller;

import com.bridgelabz.lmsapplication.dto.EmailDto;
import com.bridgelabz.lmsapplication.dto.UserDto;
import com.bridgelabz.lmsapplication.model.JwtRequest;
import com.bridgelabz.lmsapplication.model.JwtResponse;
import com.bridgelabz.lmsapplication.model.UserDetail;
import com.bridgelabz.lmsapplication.security.JwtTokenUtil;
import com.bridgelabz.lmsapplication.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController()
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtUserDetailsService service;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    //API FOR AUTHENTICATE USER
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = service
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    //METHOD FOR CHECK USER AUTHENTICATION
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

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
