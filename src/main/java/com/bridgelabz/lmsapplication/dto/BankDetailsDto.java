package com.bridgelabz.lmsapplication.dto;

import java.util.Date;

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
    private Date creatorStamp;
    private String creatorUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumberVerified() {
        return accountNumberVerified;
    }

    public void setAccountNumberVerified(String accountNumberVerified) {
        this.accountNumberVerified = accountNumberVerified;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getIfscCodeVerified() {
        return ifscCodeVerified;
    }

    public void setIfscCodeVerified(String ifscCodeVerified) {
        this.ifscCodeVerified = ifscCodeVerified;
    }

    public int getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(int panNumber) {
        this.panNumber = panNumber;
    }

    public int getAddhaarNumber() {
        return addhaarNumber;
    }

    public void setAddhaarNumber(int addhaarNumber) {
        this.addhaarNumber = addhaarNumber;
    }

    public String getAddhaarNumberVerified() {
        return addhaarNumberVerified;
    }

    public void setAddhaarNumberVerified(String addhaarNumberVerified) {
        this.addhaarNumberVerified = addhaarNumberVerified;
    }

    public Date getCreatorStamp() {
        return creatorStamp;
    }

    public void setCreatorStamp(Date creatorStamp) {
        this.creatorStamp = creatorStamp;
    }

    public String getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(String creatorUser) {
        this.creatorUser = creatorUser;
    }
}
