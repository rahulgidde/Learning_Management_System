package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.dto.EmailDto;
import com.bridgelabz.lmsapplication.dto.UserDto;
import com.bridgelabz.lmsapplication.model.JwtRequest;
import com.bridgelabz.lmsapplication.model.JwtResponse;
import com.bridgelabz.lmsapplication.model.UserDetailModel;
import com.bridgelabz.lmsapplication.repository.UserRepository;
import com.bridgelabz.lmsapplication.util.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, IUserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    //METHOD FOR FIND RECORD FORM REPOSITORY BY USERNAME
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailModel user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    //METHOD FOR LOAD USER DETAILS
    @Override
    public UserDetailModel loadUserDetails(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserDetailModel user = mapper.map(userDto, UserDetailModel.class);
        repository.save(user);
        return user;
    }

    //METHOD FOR FIND RECORD FORM REPOSITORY BY EMAIL
    @Override
    public UserDetailModel findByEmail(String email) {
        return repository.findByEmail(email);
    }

    //METHOD FOR REST PASSWORD
    @Override
    public Optional<UserDetailModel> resetPassword(String token, String password) {
        String id = jwtTokenUtil.getUsernameFromToken(token);
        return repository.findById(Long.valueOf(id))
                .map(userDetailModel -> {
                    userDetailModel.setPassword(passwordEncoder.encode(password));
                    return userDetailModel;
                })
                .map(repository::save);
    }

    //METHOD FOR SEND EMAIL
    @Override
    public void sendEmail(String emailId) throws MessagingException {
        EmailDto emailDto = new EmailDto();
        emailDto.setEmailId(emailId);
        UserDetailModel user = findByEmail(emailDto.getEmailId());
        final String token = jwtTokenUtil.generateEmailToken(user.getId());
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(message, true);
        helper.setSubject(emailDto.getSubject());
        helper.setTo(emailDto.getEmailId());
        helper.setText(token, true);
        javaMailSender.send(message);
    }

    //METHOD FOR USER AUTHENTICATION
    @Override
    public ResponseEntity<?> createAuthenticationToken(JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    //METHOD FOR CHECK USER AUTHENTICATION
    @Override
    public void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
