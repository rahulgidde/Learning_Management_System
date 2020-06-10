package com.bridgelabz.lmsapplication.util;

import com.bridgelabz.lmsapplication.dto.EmailDto;

public interface IRabbitMQ {

    public void sendMessageToQueue(EmailDto emailDto);

    public void sendMail(EmailDto emailDto);
}
