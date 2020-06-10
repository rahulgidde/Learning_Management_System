package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.dto.EmailDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class RabbitMQImpl implements IRabbitMQ{

    @Autowired
    JavaMailSender javaMailSender;

    @Qualifier("rabbitMq")
    @Autowired
    private AmqpTemplate amqpTemplate;

    private String exchange = "rabbitExchange";
    private String routingkey = "rabbitKey";

    @Override
    public void send(EmailDto emailDto) {
        amqpTemplate.convertAndSend(exchange, routingkey, emailDto);
    }

    @Override
    public void sendmail(EmailDto emailDto) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setSubject(emailDto.getSubject());
            helper.setTo(emailDto.getEmailId());
            helper.setText(emailDto.getBody(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
         javaMailSender.send(message);
    }
}

