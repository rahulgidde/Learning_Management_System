package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.dto.EmailDto;
import com.bridgelabz.lmsapplication.dto.UserDto;
import com.bridgelabz.lmsapplication.model.JwtRequest;
import com.bridgelabz.lmsapplication.model.JwtResponse;
import com.bridgelabz.lmsapplication.model.UserDetail;
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
        UserDetail user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    //METHOD FOR LOAD USER DETAILS
    @Override
    public UserDetail loadUserDetails(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserDetail user = mapper.map(userDto, UserDetail.class);
        repository.save(user);
        return user;
    }

    //METHOD FOR FIND RECORD FORM REPOSITORY BY EMAIL
    @Override
    public UserDetail findByEmail(String email) {
        UserDetail userDetail = repository.findByEmail(email);
        return userDetail;
    }

    //METHOD FOR REST PASSWORD
    @Override
    public UserDetail resetPassword(Long id, String password) {
        UserDetail user = repository.findById(id).get();
        user.setPassword(password);
        return repository.save(user);
    }

    //METHOD FOR SEND EMAIL
    @Override
    public void sendEmail(EmailDto emailDto, String token) throws MessagingException {
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
