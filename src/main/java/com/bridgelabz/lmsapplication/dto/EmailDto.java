package com.bridgelabz.lmsapplication.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmailDto implements Serializable {
    private String emailId;
    private String subject;
    private String body;
}
