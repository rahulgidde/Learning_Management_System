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
    Long id;
    Long candidateId;
    String name;
    int accountNumber;
    String accountNumberVerified;
    String ifscCode;
    String ifscCodeVerified;
    int panNumber;
    int addhaarNumber;
    String addhaarNumberVerified;
    Date creatorStamp;
    String creatorUser;
}
