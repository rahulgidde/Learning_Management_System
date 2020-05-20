package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.dto.EmailDto;
import com.bridgelabz.lmsapplication.dto.UserDto;
import com.bridgelabz.lmsapplication.model.UserDetail;
import com.bridgelabz.lmsapplication.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private JavaMailSender javaMailSender;

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
    public UserDetail loadUserDetails(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserDetail user = mapper.map(userDto, UserDetail.class);
        repository.save(user);
        return user;
    }

    //METHOD FOR FIND RECORD FORM REPOSITORY BY EMAIL
    public UserDetail findByEmail(String email) {
        UserDetail userDetail = repository.findByEmail(email);
        return userDetail;
    }

    //METHOD FOR REST PASSWORD
    public UserDetail resetPassword(Long id, String password) {
        UserDetail user = repository.findById(id).get();
        user.setPassword(password);
        return repository.save(user);
    }

    //METHOD FOR SEND EMAIL
    public void sendEmail(EmailDto emailDto, String token) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(message, true);
        helper.setSubject(emailDto.getSubject());
        helper.setTo(emailDto.getEmailId());
        helper.setText(token, true);
        javaMailSender.send(message);
    }
}
