package com.bridgelabz.lmsapplication.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BankDetailsDto {
    private Long id;
    private Long candidateId;
    private String name;
    private int accountNumber;
    private String accountNumberVerified;
    private String ifscCode;
    private String ifscCodeVerified;
    private int panNumber;
    private int addhaarNumber;
    private String addhaarNumberVerified;
    private LocalDateTime creatorStamp;
    private long creatorUser;
}
