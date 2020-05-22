package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.dto.UserDto;
import com.bridgelabz.lmsapplication.model.JwtRequest;
import com.bridgelabz.lmsapplication.model.UserDetailModel;

import javax.mail.MessagingException;
import java.util.Optional;

public interface IUserService {
    public String createAuthenticationToken(JwtRequest authenticationRequest) throws Exception;

    public void authenticate(String username, String password) throws Exception;

    public UserDetailModel loadUserDetails(UserDto userDto);

    public UserDetailModel findByEmail(String email);

    public Optional<UserDetailModel> resetPassword(String token, String password);

    public void sendEmail(String emailId) throws MessagingException;
}
