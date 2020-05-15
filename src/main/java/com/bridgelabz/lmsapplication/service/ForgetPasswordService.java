package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.dto.EmailDto;
import com.bridgelabz.lmsapplication.model.UserDetail;
import com.bridgelabz.lmsapplication.repository.LmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class ForgetPasswordService {
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    LmsRepository repository;


    public UserDetail findByEmail(String email) {
        UserDetail userDetail = repository.findByEmail(email);
        return userDetail;
    }


    public UserDetail resetPassword(Long id, String password) {
        UserDetail user = repository.findById(id).get();
        user.setPassword(password);
        return repository.save(user);
    }

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
