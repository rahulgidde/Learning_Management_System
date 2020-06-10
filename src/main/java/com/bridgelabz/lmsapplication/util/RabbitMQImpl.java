package com.bridgelabz.lmsapplication.util;

import com.bridgelabz.lmsapplication.dto.EmailDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class RabbitMQImpl implements IRabbitMQ {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${spring.rabbitmq.template.exchange}")
    private String exchangeName;

    @Value("${spring.rabbitmq.template.routing-key}")
    private String routingKey;

    @Override
    public void sendMessageToQueue(EmailDto emailDto) {
        amqpTemplate.convertAndSend(exchangeName, routingKey, emailDto);
    }

    @Override
    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
    public void sendMail(EmailDto emailDto) {
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

