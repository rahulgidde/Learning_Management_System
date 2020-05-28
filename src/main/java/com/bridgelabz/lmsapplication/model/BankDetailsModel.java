package com.bridgelabz.lmsapplication.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "candidate_bank_details")
@Table
public class BankDetailsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private Date creatorStamp;
    private String creatorUser;
}
