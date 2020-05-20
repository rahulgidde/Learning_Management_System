package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.dto.EmailDto;
import com.bridgelabz.lmsapplication.dto.UserDto;
import com.bridgelabz.lmsapplication.model.JwtRequest;
import com.bridgelabz.lmsapplication.model.UserDetail;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;

public interface IUserService {
    public ResponseEntity<?> createAuthenticationToken(JwtRequest authenticationRequest) throws Exception;

    public void authenticate(String username, String password) throws Exception;

    public UserDetail loadUserDetails(UserDto userDto);

    public UserDetail findByEmail(String email);

    public UserDetail resetPassword(Long id, String password);

    public void sendEmail(EmailDto emailDto, String token) throws MessagingException;
}
