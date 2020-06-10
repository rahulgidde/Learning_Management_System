package com.bridgelabz.lmsapplication.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class EmailDto implements Serializable {
    private String emailId;
    private String subject;
    private String body;
}
